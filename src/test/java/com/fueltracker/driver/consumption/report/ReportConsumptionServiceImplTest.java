package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import com.fueltracker.tests.type.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("A report consumption service")
@ServiceTest
class ReportConsumptionServiceImplTest {

    private static final Long DRIVER_ID = 1L;
    private static final int SUCCESSFUL_MONTH = 8;
    private static final int WRONG_MONTH = 0;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private ReportConsumptionService service;

    @Nested
    @DisplayName("of money spended")
    class moneySpended {

        @DisplayName("by month")
        @Test
        void canSearchSpendedMoney(){
            List<MoneySpendDTO> moneySpended = service.searchMoneySpendByMonth().collectList().block();
            Assertions.assertNotNull(moneySpended, "Need to return a not null list by month");
            Assertions.assertTrue(moneySpended.size() > 0, "Need to return values for the spended money by month");
        }

        @DisplayName("by month and driver")
        @Test
        void canSearchSpendedMoneyByDriver(){
            List<MoneySpendDTO> moneySpendedByDriver = service.searchMoneySpendByMonthAndDriverId(DRIVER_ID).collectList().block();
            Assertions.assertNotNull(moneySpendedByDriver, "Need to return a not null list by month and driver");
            Assertions.assertTrue(moneySpendedByDriver.size() > 0, "Need to return values for the spended money by month and driver");
        }
    }

    @Nested
    @DisplayName("listing fuel consumption")
    class fuelConsumption {

        @DisplayName("by month")
        @Test
        void canSearchFuelConsumptionReport(){
            List<FuelConsumptionDTO> consumptionByMonth = service.searchFuelConsumptionByMonth(SUCCESSFUL_MONTH).collectList().block();
            Assertions.assertNotNull(consumptionByMonth, "Need to return a not null list of consumption by month");
            Assertions.assertTrue(consumptionByMonth.size() > 0, "Need to return the consumptions by month");
        }

        @DisplayName("by month but getting error with wrong month")
        @Test
        void cannotSearchFuelConsumptionReport(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonth(WRONG_MONTH), "Need to throw exception if the month is invalid. Valid months: 1 <= month <= 12");
        }

        @DisplayName("by month and driver")
        @Test
        void canSearchFuelConsumptionByDriverReport(){
            List<FuelConsumptionDTO> consumptionByMonthAndDriver = service.searchFuelConsumptionByMonthAndDriverId(SUCCESSFUL_MONTH, DRIVER_ID).collectList().block();
            Assertions.assertNotNull(consumptionByMonthAndDriver, "Need to return a not null list of consumption by month and driver");
            Assertions.assertTrue(consumptionByMonthAndDriver.size() > 0, "Need to return the consumptions by month and driver");
        }

        @DisplayName("by month and driver but getting error with wrong month")
        @Test
        void cannotSearchFuelConsumptionByDriverReport(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonthAndDriverId(WRONG_MONTH, DRIVER_ID), "Need to throw exception if the month is invalid. Valid months: 1 <= month <= 12");
        }

        @DisplayName("by month and driver but getting error without driver")
        @Test
        void cannotSearchFuelConsumptionByDriverReportWithoutDriver(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonthAndDriverId(SUCCESSFUL_MONTH, null), "Need to throw exception if the driver is null");
        }
    }

    @Nested
    @DisplayName("listing for fuel type")
    class fueltype {

        @DisplayName("by month grouped by fuel type")
        @Test
        void canSearchFuelConsumptionGroupedByFuelTypeReport(){
            List<FuelConsumptionByFuelTypeDTO> consumptionByMonth = service.searchFuelConsumptionByMonthGroupedByFuelType(SUCCESSFUL_MONTH).collectList().block();
            Assertions.assertNotNull(consumptionByMonth, "Need to return a not null list of consumption by month grouped by fuel type");
            Assertions.assertTrue(consumptionByMonth.size() > 0, "Need to return the consumptions by month grouped by fuel type");
        }

        @DisplayName("by month grouped by fuel type but getting error with wrong month")
        @Test
        void cannotSearchFuelConsumptionGroupedByFuelTypeReport(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonthGroupedByFuelType(WRONG_MONTH), "Need to throw exception if the month is invalid. Valid months: 1 <= month <= 12");
        }

        @DisplayName("by month and driver grouped by fuel type")
        @Test
        void canSearchFuelConsumptionByDriverGroupedByFuelTypeReport(){
            List<FuelConsumptionByFuelTypeDTO> consumptionByMonthAndDriver = service.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(SUCCESSFUL_MONTH, DRIVER_ID).collectList().block();
            Assertions.assertNotNull(consumptionByMonthAndDriver, "Need to return a not null list of consumption by month and driver grouped by fuel type");
            Assertions.assertTrue(consumptionByMonthAndDriver.size() > 0, "Need to return the consumptions by month and driver grouped by fuel type");
        }

        @DisplayName("by month and driver grouped by fuel type but getting error with wrong month")
        @Test
        void cannotSearchFuelConsumptionByDriverGroupedByFuelTypeReport(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(WRONG_MONTH, DRIVER_ID), "Need to throw exception if the month is invalid. Valid months: 1 <= month <= 12'");
        }

        @DisplayName("by month and driver grouped by fuel type but getting error without driver")
        @Test
        void cannotSearchFuelConsumptionByDriverGroupedByFuelTypeReportWithoutDriver(){
            Assertions.assertThrows( RuntimeException.class, () -> service.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(SUCCESSFUL_MONTH, null), "Need to throw exception if the driver is null");
        }
    }
}
