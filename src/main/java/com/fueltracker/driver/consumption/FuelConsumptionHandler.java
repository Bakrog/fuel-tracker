package com.fueltracker.driver.consumption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
public class FuelConsumptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(FuelConsumptionHandler.class);

    private static final String FILE_KEY_FOR_BULK_INSERT = "bulk";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");

    private final FuelConsumptionService service;

    @Autowired
    public FuelConsumptionHandler(FuelConsumptionService service) {
        this.service = service;
    }

    Mono<ServerResponse> findAllFuelConsumption(final ServerRequest request){
        LOGGER.info("Getting all fuel consumption.");
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.findAll(), FuelConsumption.class));
    }

    Mono<ServerResponse> saveFuelConsumption(final ServerRequest request){
        LOGGER.info("Saving fuel consumption.");
        return request.bodyToMono(FuelConsumptionDTO.class).flatMap(fuelConsumptionDTO -> ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.save(fuelConsumptionDTO), FuelConsumption.class)));
    }

    Mono<ServerResponse> importFuelConsumption(ServerRequest request){
        LOGGER.info("Bulk saving fuel consumption.");
        return request.body(BodyExtractors.toMultipartData()).flatMap(parts -> {
            Map<String, Part> map =parts.toSingleValueMap();
            if(!map.containsKey(FILE_KEY_FOR_BULK_INSERT)){
                throw new RuntimeException("The parameter 'bulk' is obrigatory and needs to be a file.");
                //ServerResponse.badRequest().contentType(APPLICATION_JSON_UTF8).body(BodyInserters.fromObject());
            } else if (!(map.get("bulk") instanceof FilePart)){
                throw new RuntimeException("The parameter 'bulk' needs to be a file.");
            }
            final FilePart bulk = (FilePart) map.get("bulk");
            try {
                final String tempFileName = String.format("%s_%s", bulk.filename(), dtf.format(LocalDateTime.now()));
                final Path tempFile = Files.createTempFile( tempFileName, ".csv" );
                bulk.transferTo(tempFile.toFile());
                return ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON_UTF8)
                        .body(BodyInserters.fromPublisher(service.saveBulk(tempFile), FuelConsumption.class));
            } catch (IOException e) {
                final String error = "Error while receiving the file. Try again later.";
                LOGGER.error(error, e);
                throw new RuntimeException(error, e);
            }
        });
    }
}
