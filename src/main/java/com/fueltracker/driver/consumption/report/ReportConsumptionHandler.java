package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Component
public class ReportConsumptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ReportConsumptionHandler.class);

    private final ReportConsumptionService service;

    @Autowired
    public ReportConsumptionHandler(ReportConsumptionService service) {
        this.service = service;
    }

    Mono<ServerResponse> searchMoneySpendByMonth(final ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.searchMoneySpendByMonth(), MoneySpendDTO.class));
    }

    Mono<ServerResponse> searchMoneySpendByMonthAndDriverId(final ServerRequest request){
        final String driver = request.pathVariable("driver");
        final Long driverId = driver != null && driver.length() > 0 ? Long.valueOf(driver) : null;
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(service.searchMoneySpendByMonthAndDriverId(driverId), MoneySpendDTO.class));
    }

    Mono<ServerResponse> searchFuelConsumptionByMonth(final ServerRequest request){
        final String monthString = request.pathVariable("month");
        try {
            final int month = Integer.parseInt(monthString);
            return ServerResponse
                    .ok()
                    .contentType(APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromPublisher(service.searchFuelConsumptionByMonth(month), FuelConsumptionDTO.class));
        } catch (NumberFormatException nfe) {
            return wrongMonthInformed(monthString, nfe);
        }
    }

    Mono<ServerResponse> searchFuelConsumptionByMonthAndDriverId(final ServerRequest request){
        final String driver = request.pathVariable("driver");
        final Long driverId = driver != null && driver.length() > 0 ? Long.valueOf(driver) : null;
        final String monthString = request.pathVariable("month");
        try {
            final int month = Integer.parseInt(monthString);
            return ServerResponse
                    .ok()
                    .contentType(APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromPublisher(service.searchFuelConsumptionByMonthAndDriverId(month, driverId), FuelConsumptionDTO.class));
        } catch (NumberFormatException nfe){
            return wrongMonthInformed(monthString, nfe);
        }
    }

    Mono<ServerResponse> searchFuelConsumptionByMonthGroupedByFuelType(final ServerRequest request){
        final String monthString = request.pathVariable("month");
        try {
            final int month = Integer.parseInt(monthString);
            return ServerResponse
                    .ok()
                    .contentType(APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromPublisher(service.searchFuelConsumptionByMonthGroupedByFuelType(month), FuelConsumptionByFuelTypeDTO.class));
        } catch (NumberFormatException nfe){
            return wrongMonthInformed(monthString, nfe);
        }
    }

    Mono<ServerResponse> searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(final ServerRequest request){
        final String driver = request.pathVariable("driver");
        final Long driverId = driver != null && driver.length() > 0 ? Long.valueOf(driver) : null;
        final String monthString = request.pathVariable("month");
        try {
            final int month = Integer.parseInt(monthString);
            return ServerResponse
                    .ok()
                    .contentType(APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromPublisher(service.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(month, driverId), FuelConsumptionByFuelTypeDTO.class));
        } catch (NumberFormatException nfe){
            return wrongMonthInformed(monthString, nfe);
        }
    }

    private Mono<ServerResponse> wrongMonthInformed(String monthString, NumberFormatException nfe) {
        LOGGER.error(String.format("Wrong month value informed: %s", monthString), nfe);
        throw new RuntimeException(String.format("The month informed '%s' its not a valid month. Valid months: 1 <= month <= 12", monthString));
    }
}
