package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumption;
import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository to get reports from {@link FuelConsumption}
 */
@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Repository
public interface ReportConsumptionRepository extends JpaRepository<FuelConsumption, Long> {

    /**
     * Find the money spend by month of the {@link FuelConsumption}
     * @return list of {@link MoneySpendDTO}
     */
    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.report.MoneySpendDTO(" +
            "  month(fc.date), " +
            "  sum(fc.pricePerLiter * fc.volumeInLiters)) " +
            "FROM " +
            "  FuelConsumption fc " +
            "GROUP BY " +
            "  month(fc.date) ")
    List<MoneySpendDTO> searchMoneySpendByMonth();

    /**
     * Find the money spend by month of the {@link FuelConsumption} by driver
     * @param driverId {@link Long} - the id of the driver
     * @return list of {@link MoneySpendDTO} of the driver
     */
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
    List<MoneySpendDTO> searchMoneySpendByMonthAndDriverId(@Param(value = "driverId") Long driverId);

    /**
     * Find the {@link FuelConsumption} list of the provided month
     * @param month month of the year (0 - 11)
     * @return list of {@link FuelConsumptionDTO}
     */
    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.FuelConsumptionDTO( " +
            "  ft.name, " +
            "  fc.date, " +
            "  fc.pricePerLiter, " +
            "  fc.volumeInLiters, " +
            "  fc.driverId " +
            ") " +
            "FROM " +
            "  FuelConsumption fc " +
            "  JOIN fc.fuelType ft " +
            "WHERE" +
            "  month(fc.date) = :month ")
    List<FuelConsumptionDTO> searchFuelConsumptionByMonth(@Param(value = "month") int month);

    /**
     * Find the {@link FuelConsumption} list of the provided month by driver
     * @param month month of the year (0 - 11)
     * @param driverId {@link Long} - the id of the driver
     * @return list of {@link FuelConsumptionDTO}
     */
    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.FuelConsumptionDTO( " +
            "  ft.name, " +
            "  fc.date, " +
            "  fc.pricePerLiter, " +
            "  fc.volumeInLiters, " +
            "  fc.driverId " +
            ") " +
            "FROM " +
            "  FuelConsumption fc " +
            "  JOIN fc.fuelType ft " +
            "WHERE" +
            "  month(fc.date) = :month " +
            "  AND fc.driverId = :driverId ")
    List<FuelConsumptionDTO> searchFuelConsumptionByMonthAndDriverId(@Param(value = "month") int month, @Param(value = "driverId") Long driverId);

    /**
     * Find the {@link FuelConsumptionByFuelType} list of the provided month
     * @param month month of the year (0 - 11)
     * @return list of {@link FuelConsumptionByFuelType}
     */
    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.report.FuelConsumptionByFuelType( " +
            "  ft.name, " +
            "  sum(fc.volumeInLiters), " +
            "  avg(fc.pricePerLiter), " +
            "  sum(fc.pricePerLiter * fc.volumeInLiters) " +
            ") " +
            "FROM " +
            "  FuelConsumption fc " +
            "  JOIN fc.fuelType ft " +
            "WHERE" +
            "  month(fc.date) = :month " +
            "GROUP BY " +
            "  ft.name")
    List<FuelConsumptionByFuelType> searchFuelConsumptionByMonthGroupedByFuelType(@Param(value = "month") int month);

    /**
     * Find the {@link FuelConsumptionByFuelType} list of the provided month by driver
     * @param month month of the year (0 - 11)
     * @param driverId {@link Long} - the id of the driver
     * @return list of {@link FuelConsumptionByFuelType} by driver
     */
    @Query(value = "" +
            "SELECT new com.fueltracker.driver.consumption.report.FuelConsumptionByFuelType( " +
            "  ft.name, " +
            "  sum(fc.volumeInLiters), " +
            "  avg(fc.pricePerLiter), " +
            "  sum(fc.pricePerLiter * fc.volumeInLiters) " +
            ") " +
            "FROM " +
            "  FuelConsumption fc " +
            "  JOIN fc.fuelType ft " +
            "WHERE" +
            "  month(fc.date) = :month " +
            "  AND fc.driverId = :driverId " +
            "GROUP BY " +
            "  ft.name")
    List<FuelConsumptionByFuelType> searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(@Param(value = "month") int month, @Param(value = "driverId") Long driverId);
}
