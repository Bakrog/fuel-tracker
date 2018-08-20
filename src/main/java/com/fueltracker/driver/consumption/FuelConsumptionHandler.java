package com.fueltracker.driver.consumption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
public class FuelConsumptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(FuelConsumptionHandler.class);

    private final FuelConsumptionService service;

    @Autowired
    public FuelConsumptionHandler(FuelConsumptionService service) {
        this.service = service;
    }

    Mono<ServerResponse> findAllFuelConsumption(final ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.findAll(), FuelConsumption.class));
    }

    Mono<ServerResponse> saveFuelConsumption(final ServerRequest request){
        return request.bodyToMono(FuelConsumptionDTO.class).flatMap(fuelConsumptionDTO -> ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.save(fuelConsumptionDTO), FuelConsumption.class)));
    }

    /*public Mono<ServerResponse> importFuelConsumption(ServerRequest request){
        return request.body(BodyExtractors.toMultipartData()).flatMap(parts -> {
            Map<String, Part> map =parts.toSingleValueMap();
        });
    }*/
}
