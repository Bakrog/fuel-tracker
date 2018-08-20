package com.fueltracker.driver.consumption;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to make crud operations, searchs, others with {@link FuelConsumption}
 */
@Transactional
public interface FuelConsumptionRepository extends CrudRepository<FuelConsumption, Long> {
}
