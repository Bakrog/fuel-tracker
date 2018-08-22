package com.fueltracker.driver.consumption.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.ALL;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReportConsumptionRouter {

    @Bean
    public RouterFunction<ServerResponse> reportRoute(final ReportConsumptionHandler handler){
        return nest(path("/"),
                nest(path("/report"),
                        nest(path("/spended-by-month"),
                                route(
                                        GET("/")
                                                .and(accept(ALL)),
                                        handler::searchMoneySpendByMonth)
                                        .andRoute(
                                                GET("/{driver}")
                                                        .and(accept(ALL)),
                                                handler::searchMoneySpendByMonthAndDriverId)
                        )
                                .andNest(path("/fuel-consumption/{month}"),
                                        route(
                                                GET("/")
                                                        .and(accept(ALL)),
                                                handler::searchFuelConsumptionByMonth)
                                                .andRoute(
                                                        GET("/{driver}")
                                                                .and(accept(ALL)),
                                                        handler::searchFuelConsumptionByMonthAndDriverId)
                                )
                                .andNest(path("/fuel-consumption-by-fuel-type/{month}"),
                                        route(
                                                GET("/")
                                                        .and(accept(ALL)),
                                                handler::searchFuelConsumptionByMonthGroupedByFuelType)
                                                .andRoute(
                                                        GET("/{driver}")
                                                                .and(accept(ALL)),
                                                        handler::searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType)
                                )
                )
        );
    }
}
