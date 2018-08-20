package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Repository
public interface ReportConsumptionRepository extends JpaRepository<FuelConsumption, Long> {
}
