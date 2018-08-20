package com.fueltracker.driver.consumption;

import reactor.core.publisher.Mono;

/**
 * Interface responsable to provide bussiness logic for the fuel type
 */
public interface FuelTypeService {

    /**
     * Save a new fuel type name
     * @param name the name of the fuel type
     * @return the saved fuel type
     */
    Mono<FuelType> save(final String name);

    /**
     * Save a new fuel type
     * @param fuelType the fuel type to save
     * @return the saved fuel type
     */
    Mono<FuelType> save(final FuelType fuelType);
}
