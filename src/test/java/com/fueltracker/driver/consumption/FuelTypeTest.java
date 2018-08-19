package com.fueltracker.driver.consumption;

import com.fueltracker.tests.type.ModelTest;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("A fuel type")
@ModelTest
class FuelTypeTest {

    private static final String NAME = "FuelTypeName";

    private FuelType fuelType;

    @BeforeAll
    void before(){
        this.fuelType = Mockito.mock(FuelType.class);
        Mockito.when(fuelType.getName()).thenReturn(NAME);
    }

    @DisplayName("has a valid name")
    @Test
    void testName(){
        this.fuelType.setName(NAME);
        Assertions.assertEquals(NAME, this.fuelType.getName(), "Name is not valid");
    }
}
