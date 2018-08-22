package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import com.fueltracker.tests.type.DatabaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("A report consumption repository")
@DatabaseTest
class ReportConsumptionRepositoryTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private ReportConsumptionRepository repository;

    @DisplayName("can report total spend by month")
    @Test
    void totalSpendByMonth(){
        List<MoneySpendDTO> spendList = repository.searchMoneySpendByMonth();
        Assertions.assertNotNull(spendList, "Report spend by month cannot be null");
        Assertions.assertTrue(spendList.size() > 0, "Report spend by month needs to retrieve data from the database");
    }

    @DisplayName("can report total spend by month by driver")
    @Test
    void totalSpendByMonthByDriver(){
        List<MoneySpendDTO> spendList = repository.searchMoneySpendByMonthAndDriverId(2L);
        Assertions.assertNotNull(spendList, "Report spend by month cannot be null");
        Assertions.assertTrue(spendList.size() > 0, "Report spend by month needs to retrieve data from the database");
        Assertions.assertEquals(1, spendList.size(), "Report spend by month and driver 2 can only be 1");
    }

    @DisplayName("can report fuel consumption by month")
    @Test
    void fuelConsumptionByMonth(){
        List<FuelConsumptionDTO> consumptions = repository.searchFuelConsumptionByMonth(8);
        Assertions.assertNotNull(consumptions, "Report fuel consumption cannot be null");
        Assertions.assertTrue(consumptions.size() > 0, "Report fuel consumption by month needs to retrieve data from the database");
    }

    @DisplayName("can report fuel consumption by month by driver")
    @Test
    void fuelConsumptionByMonthByDriver(){
        List<FuelConsumptionDTO> consumptions = repository.searchFuelConsumptionByMonthAndDriverId(8, 2L);
        Assertions.assertNotNull(consumptions, "Report spend by month cannot be null");
        Assertions.assertTrue(consumptions.size() > 0, "Report fuel consumption by month needs to retrieve data from the database");
        Assertions.assertEquals(2, consumptions.size(), "Report fuel consumption by month and driver 2 can only be 1");
    }

    @DisplayName("can report fuel consumption by month grouped by fueltype")
    @Test
    void fuelConsumptionByMonthGroupedByFuelType(){
        List<FuelConsumptionByFuelTypeDTO> consumptions = repository.searchFuelConsumptionByMonthGroupedByFuelType(8);
        Assertions.assertNotNull(consumptions, "Report fuel consumption cannot be null");
        Assertions.assertTrue(consumptions.size() > 0, "Report fuel consumption by month needs to retrieve data from the database");
    }

    @DisplayName("can report fuel consumption by month by driver grouped by fueltype")
    @Test
    void fuelConsumptionByMonthByDriverGroupedByFuelType(){
        List<FuelConsumptionByFuelTypeDTO> consumptions = repository.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(8, 2L);
        Assertions.assertNotNull(consumptions, "Report spend by month grouped by fueltype cannot be null");
        Assertions.assertTrue(consumptions.size() > 0, "Report fuel consumption by month grouped by fueltype needs to retrieve data from the database");
    }
}
