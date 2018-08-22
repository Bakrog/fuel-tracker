package com.fueltracker.driver.consumption;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

/**
 * Interface responsible to provide bussiness logic for the fuel consumption
 */
public interface FuelConsumptionService {

    /**
     * Verify if a fuel consumption entry is valid
     * @param fuelConsumptionDTO to be verified
     */
    boolean validateFuelConsumptionEntry(final FuelConsumptionDTO fuelConsumptionDTO);

    /**
     * Find all fuel consumptions.
     * @return all the fuel consumptions.
     */
    Flux<FuelConsumption> findAll();

    /**
     * Save one fuel consumption.
     * @param fuelConsumptionDTO the fuel consumption to save
     * @throws IllegalArgumentException if the validation is false {@link FuelConsumptionService#validateFuelConsumptionEntry(FuelConsumptionDTO)}
     * @return the saved fuel consumption or exception
     */
    Mono<FuelConsumption> save(final FuelConsumptionDTO fuelConsumptionDTO) throws IllegalArgumentException;

    /**
     * Save a bulk of fuel consumption present in a CSV file.
     * @param filePath the {@link Path} to the CSV file
     * @throws IllegalArgumentException if the validation is false {@link FuelConsumptionService#validateFuelConsumptionEntry(FuelConsumptionDTO)}
     * @return the fuel consumption objects saved
     */
    Flux<FuelConsumption> saveBulk(final Path filePath) throws IllegalArgumentException;

    /**
     * Convert consumption DTO into an consumption mapped object
     * @param fuelConsumptionDTO the {@link FuelConsumptionDTO} of the consumption
     * @param fuelType the {@link FuelType} of the consumption
     * @return {@link FuelConsumption}
     */
    FuelConsumption convert(final FuelConsumptionDTO fuelConsumptionDTO, final FuelType fuelType);

    /**
     * Converts a line of a CSV file to a {@link FuelConsumption}
     * @param line the line of the file that will be converted
     * @return {@link FuelConsumption}
     */
    FuelConsumptionDTO convertToDTO(final String line);
}
