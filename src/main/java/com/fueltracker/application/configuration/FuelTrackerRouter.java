package com.fueltracker.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Configure the routers of the application
 */
@Configuration
public class FuelTrackerRouter implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> applicationRoute(){
        // TODO: Create application routes
        return route(GET("/"), serverRequest -> ServerResponse
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(BodyInserters.fromObject("<html><body><h1>Fuel Tracker</h1></body></html>")) );
    }
}
