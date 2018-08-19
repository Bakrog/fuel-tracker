package com.fueltracker.application;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("A fuel tracker")
public class FuelTrackerApplicationTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("is online")
    public void isOnline(){
        webTestClient.get().uri("/").accept(MediaType.TEXT_HTML).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("<html><body><h1>Fuel Tracker</h1></body></html>");
    }
}
