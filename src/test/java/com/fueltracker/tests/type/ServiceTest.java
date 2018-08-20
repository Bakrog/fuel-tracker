package com.fueltracker.tests.type;

import com.fueltracker.application.FuelTrackerApplication;
import com.fueltracker.application.configuration.PersistenceConfig;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("service-test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FuelTrackerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public @interface ServiceTest {
}
