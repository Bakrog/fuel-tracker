package com.fueltracker.driver.consumption;

import com.fueltracker.tests.type.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DisplayName("A fuel consumption service")
@ServiceTest
class FuelConsumptionServiceImplTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private FuelConsumptionService service;

    @Nested
    @DisplayName("validates")
    class validates {

        @Test
        @DisplayName("if fuel consumption is valid")
        void isValid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("Test" , LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, Long.MIN_VALUE);
            Assertions.assertTrue(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption is not valid");
        }

        @Test
        @DisplayName("if fuel type attribute of fuel consumption is null and invalid")
        void isFuelTypeNullInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO(null , LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, Long.MIN_VALUE);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption fuel type is valid");
        }

        @Test
        @DisplayName("if fuel type attribute of fuel consumption is empty and invalid")
        void isFuelTypeEmptyInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("" , LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, Long.MIN_VALUE);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption fuel type is valid");
        }

        @Test
        @DisplayName("if date attribute of fuel consumption is invalid")
        void isDateInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("Test" , null, BigDecimal.TEN, BigDecimal.TEN, Long.MIN_VALUE);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption date is valid");
        }

        @Test
        @DisplayName("if price per liter attribute of fuel consumption is invalid")
        void isPricePerLiterInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("Test" , LocalDateTime.now(), null, BigDecimal.TEN, Long.MIN_VALUE);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption price per liter is valid");
        }

        @Test
        @DisplayName("if volume in liters attribute of fuel consumption is invalid")
        void isVolumeInLitersInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("Test" , LocalDateTime.now(), BigDecimal.TEN, null, Long.MIN_VALUE);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption volume in liters is valid");
        }

        @Test
        @DisplayName("if driver id attribute of fuel consumption is invalid")
        void isDriverIdInvalid(){
            final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("Test" , LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, null);
            Assertions.assertFalse(service.validateFuelConsumptionEntry(fuelConsumptionDTO), "The fuel consumption driver id is valid");
        }

    }

    @Test
    @DisplayName("can find all fuel consumption")
    void findAll(){
        final Flux<FuelConsumption> fuelConsumptionFlux = service.findAll();
        Assertions.assertNotNull(fuelConsumptionFlux, "FuelConsumptionService.findAll cant return a null Flux");
        final List<FuelConsumption> consumptions = fuelConsumptionFlux.collectList().block();
        Assertions.assertNotNull(consumptions, "FuelConsumptionService.findAll cant return a null List");
        Assertions.assertTrue(consumptions.size() > 0, "FuelConsumptionService.findAll returned an empty list");
    }

    @Test
    @DisplayName("can save a fuel consumption")
    void save(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO("95", LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, 123L);
        final FuelConsumption consumption = service.save(consumptionDTO).block();
        Assertions.assertNotNull(consumption, "Consumption saved cannot be null");
        Assertions.assertNotNull(consumption.getId(), "Consumption returned by the save method cannot have attribute ID null");
    }

    @Test
    @DisplayName("cannot save if fuel type is null")
    void cantSaveWithoutFuelType(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO(null, LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, 123L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(consumptionDTO), "The server needs to throw execption if Fuel Type is not present");
    }

    @Test
    @DisplayName("cannot save if date is null")
    void cantSaveWithoutDate(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO("95", null, BigDecimal.TEN, BigDecimal.TEN, 123L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(consumptionDTO), "The server needs to throw execption if date is not present");
    }

    @Test
    @DisplayName("cannot save if price per liter is null")
    void cantSaveWithoutPricePerLiter(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO("95", LocalDateTime.now(), null, BigDecimal.TEN, 123L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(consumptionDTO), "The server needs to throw execption if Price Per Liter is not present");
    }

    @Test
    @DisplayName("cannot save if volume in liters is null")
    void cantSaveWithoutVolumeInLiters(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO("95", LocalDateTime.now(), BigDecimal.TEN, null, 123L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(consumptionDTO), "The server needs to throw execption if Volume In Liters is not present");
    }

    @Test
    @DisplayName("cannot save if driver id is null")
    void cantSaveWithoutDriverId(){
        final FuelConsumptionDTO consumptionDTO = new FuelConsumptionDTO("95", LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(consumptionDTO), "The server needs to throw execption if driver id is not present");
    }

    @Test
    @DisplayName("can convert a DTO")
    void convertDTO(){
        final FuelType fuelType = Mockito.mock(FuelType.class);
        final FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO("95", LocalDateTime.now(), BigDecimal.TEN, BigDecimal.TEN, 123L);
        final Object converted = service.convertDTO(fuelConsumptionDTO, fuelType);
        Assert.notNull(converted, "The result object of conversion cannot be null");
        Assert.isInstanceOf(FuelConsumption.class, converted, "The result object of conversion needs to be an Fuel Consumption");
    }
}
