package com.fueltracker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fueltracker.*"})
@EnableWebFlux
@EnableAspectJAutoProxy
public class FuelTrackerApplication {
    public static void main(String[] args){
        SpringApplication.run(FuelTrackerApplication.class, args);
    }
}
