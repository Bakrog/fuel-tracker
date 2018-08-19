package com.fueltracker.application.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.fueltracker.driver.consumption"})
@EntityScan(basePackages = {"com.fueltracker.driver.consumption"})
public class PersistenceConfig {
}
