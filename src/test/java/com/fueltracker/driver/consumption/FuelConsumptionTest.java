package com.fueltracker.driver.consumption;

import com.fueltracker.tests.type.ModelTest;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("A fuel consumption")
@ModelTest
class FuelConsumptionTest {

    private static final Long FUEL_TYPE_ID = 1L;
    private static final BigDecimal PRICE_PER_LITER = BigDecimal.TEN;
    private static final BigDecimal VOLUME_IN_LITERS = BigDecimal.TEN;
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();
    private static final Long DRIVER_ID = 1L;

    private FuelConsumption fuelConsumption;

    @BeforeAll
    void before(){
        this.fuelConsumption = Mockito.mock(FuelConsumption.class);
        Mockito.when(fuelConsumption.getFuelTypeId()).thenReturn(FUEL_TYPE_ID);
        Mockito.when(fuelConsumption.getPricePerLiter()).thenReturn(PRICE_PER_LITER);
        Mockito.when(fuelConsumption.getVolumeInLiters()).thenReturn(VOLUME_IN_LITERS);
        Mockito.when(fuelConsumption.getDate()).thenReturn(DATE_TIME);
        Mockito.when(fuelConsumption.getDriveId()).thenReturn(DRIVER_ID);
    }

    @DisplayName("has a valid fuel type id")
    @Test
    void testFuelTypeId(){
        this.fuelConsumption.setFuelTypeId(FUEL_TYPE_ID);
        Assertions.assertEquals(FUEL_TYPE_ID, this.fuelConsumption.getFuelTypeId(), "Fuel type id is not valid");
    }

    @DisplayName("has a valid price per liter")
    @Test
    void testPricePerLiter(){
        this.fuelConsumption.setPricePerLiter(PRICE_PER_LITER);
        Assertions.assertEquals(PRICE_PER_LITER, this.fuelConsumption.getPricePerLiter(), "Price per liter is not valid");
    }

    @DisplayName("has a valid volume in liters")
    @Test
    void testVolumeInLiters(){
        this.fuelConsumption.setVolumeInLiters(VOLUME_IN_LITERS);
        Assertions.assertEquals(VOLUME_IN_LITERS, this.fuelConsumption.getVolumeInLiters(), "Volume in liters is not valid");
    }

    @DisplayName("has a valid date")
    @Test
    void testDate(){
        this.fuelConsumption.setDate(DATE_TIME);
        Assertions.assertEquals(DATE_TIME, this.fuelConsumption.getDate(), "Date is not valid");
    }

    @DisplayName("has a valid driver id")
    @Test
    void testDriverId(){
        this.fuelConsumption.setDriveId(DRIVER_ID);
        Assertions.assertEquals(DRIVER_ID, this.fuelConsumption.getDriveId(), "Driver id is not valid");
    }
}
