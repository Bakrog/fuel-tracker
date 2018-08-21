package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumptionRepository;
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

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private FuelConsumptionRepository consumptionRepository;

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
        List<MoneySpendDTO> spendList = repository.searchMoneySpendByMonthByDriverId(2L);
        Assertions.assertNotNull(spendList, "Report spend by month cannot be null");
        Assertions.assertTrue(spendList.size() > 0, "Report spend by month needs to retrieve data from the database");
        Assertions.assertEquals(1, spendList.size(), "Report spend by month by driver 2 can only be 1");
    }
}
