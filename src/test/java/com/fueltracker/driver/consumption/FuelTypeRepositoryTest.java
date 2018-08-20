package com.fueltracker.driver.consumption;

import com.fueltracker.tests.type.DatabaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@DisplayName("A fuel type repository")
@DatabaseTest
class FuelTypeRepositoryTest {

    private static final String NAME = "95";

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private FuelTypeRepository repository;

    @Test
    @DisplayName("can find by name")
    void canFindByName(){
        Optional<FuelType> fuelTypeOption = repository.findByName(NAME);
        Assertions.assertTrue(fuelTypeOption.isPresent(), "Fuel type was not find on database");
        Assertions.assertEquals(NAME, fuelTypeOption.get().getName(), "The fuel type name is different of the provided");
    }


    @Test
    @DisplayName("can't delete existing value without usage")
    void cantDelete(){
        Optional<FuelType> fuelTypeOption = repository.findByName(NAME);
        Assertions.assertTrue(fuelTypeOption.isPresent(), "Fuel type was not find on database");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.deleteById(fuelTypeOption.get().getId()), "Removing linked fuel type doesnt throw a exception");
    }
}
