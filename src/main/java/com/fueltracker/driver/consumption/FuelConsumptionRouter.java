package com.fueltracker.driver.consumption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.ALL;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class FuelConsumptionRouter {

    @Bean
    public RouterFunction<ServerResponse> fuelConsumptionRoute(final FuelConsumptionHandler fuelConsumptionHandler){
        return RouterFunctions.nest(path("/fuel-consumption"),
                RouterFunctions
                        .route(
                                GET("/")
                                        .and(accept(ALL))
                                        .and(contentType(APPLICATION_JSON_UTF8)),
                                fuelConsumptionHandler::findAllFuelConsumption)
                        .andRoute(
                                POST("/")
                                        .and(accept(APPLICATION_JSON_UTF8))
                                        .and(contentType(APPLICATION_JSON_UTF8)),
                                fuelConsumptionHandler::saveFuelConsumption)
                        .andRoute(
                                POST("/")
                                .and(accept(MULTIPART_FORM_DATA)),
                                fuelConsumptionHandler::importFuelConsumption)
        );
    }
}
