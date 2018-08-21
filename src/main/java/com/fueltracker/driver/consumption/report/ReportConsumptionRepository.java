package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Repository
public interface ReportConsumptionRepository extends JpaRepository<FuelConsumption, Long> {

    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.report.MoneySpendDTO(" +
            "  month(fc.date), " +
            "  sum(fc.pricePerLiter * fc.volumeInLiters)) " +
            "FROM " +
            "  FuelConsumption fc " +
            "GROUP BY " +
            "  month(fc.date) ")
    List<MoneySpendDTO> searchMoneySpendByMonth();

    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.report.MoneySpendDTO(" +
            "  month(fc.date), " +
            "  sum(fc.pricePerLiter * fc.volumeInLiters)) " +
            "FROM " +
            "  FuelConsumption fc " +
            "WHERE " +
            "  fc.driverId = :driverId " +
            "GROUP BY " +
            "  month(fc.date) ")
    List<MoneySpendDTO> searchMoneySpendByMonthByDriverId(@Param(value = "driverId") Long driverId);
}
