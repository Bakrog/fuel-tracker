package com.fueltracker.application;

import com.fueltracker.tests.type.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("A fuel tracker")
@ControllerTest
class FuelTrackerApplicationTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("is online")
    void isOnline(){
        webTestClient
                .get()
                .uri("/")
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("<html><body><h1>Fuel Tracker</h1></body></html>");
    }
}
