package com.fueltracker.driver.consumption;

import com.fueltracker.tests.type.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@DisplayName("A fuel type service")
@ServiceTest
class FuelTypeServiceImplTest {

    private static final String NAME_EXISTS = "95";

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private FuelTypeService service;

    @Nested
    @DisplayName("can save")
    class save {

        @Test
        @DisplayName("by name")
        void saveByName(){
            final String name = "Test Service By Name";
            final FuelType savedFuelType = service.save(name).block();
            Assertions.assertNotNull(savedFuelType, "Fuel Type was not saved by name correctly");
            Assertions.assertNotNull(savedFuelType.getId(), "Fuel Type was not saved by name correctly, the ID is null");
        }

        @Test
        @DisplayName("by name but if the same name exists return it")
        void failSaveByNameExistsButReturnTheExisting(){
            final FuelType fuelType = Assertions.assertDoesNotThrow(() -> service.save(NAME_EXISTS).block(), "Fuel type could be saved with a name that already exists.");
            Assertions.assertNotNull(fuelType.getId(), "The fuel type returned cant have an ID null");
        }

        @Test
        @DisplayName("by object")
        void saveByObject(){
            final String name = "Test Service By Object";
            final FuelType fuelType = new FuelType(name);
            final FuelType savedFuelType = service.save(fuelType).block();
            Assertions.assertNotNull(savedFuelType, "Fuel Type was not saved by object correctly");
            Assertions.assertNotNull(savedFuelType.getId(), "Fuel Type was not saved by object correctly, the ID is null");
        }

        @Test
        @DisplayName("by object but fail if the same name exists")
        void failSaveByObjectExists(){
            final FuelType fuelType = new FuelType(NAME_EXISTS);
            Assertions.assertThrows(DataIntegrityViolationException.class, () -> service.save(fuelType), "Fuel type could be saved with a name that already exists.");
        }
    }
}
