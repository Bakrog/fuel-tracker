package com.fueltracker.driver.consumption;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {

    /**
     * Find a fuel type by name
     * @param name of the fuel type
     * @return an {@link Optional}  fuel type
     */
    Optional<FuelType> findByName(final String name);
}
