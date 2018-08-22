package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumption;
import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import reactor.core.publisher.Flux;

/**
 * Interface responsible to provide bussiness logic for the fuel consumption reports
 */
public interface ReportConsumptionService {

    /**
     * Find the money spend by month of the {@link FuelConsumption}
     * @return {@link Flux} of {@link MoneySpendDTO}
     */
    Flux<MoneySpendDTO> searchMoneySpendByMonth();

    /**
     * Find the money spend by month of the {@link FuelConsumption} by driver
     * @param driverId {@link Long} - the id of the driver
     * @return {@link Flux} of {@link MoneySpendDTO} of the driver
     */
    Flux<MoneySpendDTO> searchMoneySpendByMonthAndDriverId(final Long driverId);

    /**
     * Find the {@link FuelConsumption} list of the provided month
     * @param month month of the year (0 - 11)
     * @return {@link Flux} of {@link FuelConsumptionDTO}
     */
    Flux<FuelConsumptionDTO> searchFuelConsumptionByMonth(final int month);

    /**
     * Find the {@link FuelConsumption} list of the provided month by driver
     * @param month month of the year (0 - 11)
     * @param driverId {@link Long} - the id of the driver
     * @return {@link Flux} of {@link FuelConsumptionDTO}
     */
    Flux<FuelConsumptionDTO> searchFuelConsumptionByMonthAndDriverId(final int month, final Long driverId);

    /**
     * Find the {@link FuelConsumptionByFuelTypeDTO} list of the provided month
     * @param month month of the year (0 - 11)
     * @return {@link Flux} of {@link FuelConsumptionByFuelTypeDTO}
     */
    Flux<FuelConsumptionByFuelTypeDTO> searchFuelConsumptionByMonthGroupedByFuelType(final int month);

    /**
     * Find the {@link FuelConsumptionByFuelTypeDTO} list of the provided month by driver
     * @param month month of the year (0 - 11)
     * @param driverId {@link Long} - the id of the driver
     * @return {@link Flux} of {@link FuelConsumptionByFuelTypeDTO} by driver
     */
    Flux<FuelConsumptionByFuelTypeDTO> searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(final int month, final Long driverId);
}
