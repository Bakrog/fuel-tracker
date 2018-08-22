package com.fueltracker.driver.consumption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FuelConsumptionRouter {

    @Bean
    public RouterFunction<ServerResponse> fuelConsumptionRoute(final FuelConsumptionHandler fuelConsumptionHandler){
        return nest(path("/"),
                nest(path("/fuel-consumption"),
                        route(
                                GET("/")
                                        .and(accept(ALL)),
                                fuelConsumptionHandler::findAllFuelConsumption)
                                .andRoute(
                                        POST("/")
                                                .and(accept(APPLICATION_JSON_UTF8)),
                                        fuelConsumptionHandler::saveFuelConsumption)
                                .andRoute(
                                        POST("/")
                                                .and(accept(MULTIPART_FORM_DATA)),
                                        fuelConsumptionHandler::importFuelConsumption)
                )
        );
    }
}
