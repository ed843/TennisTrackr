package com.ericduncandev.TennisTrackr.TennisGame;

import com.ericduncandev.TennisTrackr.TennisGame.Records.TennisMatches;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TennisControllerIntTest {
    @LocalServerPort
    int randomServerPort;

    RestClient restClient;

    @BeforeEach
    void setup() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldFindAllTennisMatches() {
        List<TennisMatches> tennisMatchesList = restClient.get()
                .uri("/api/matches")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        assert tennisMatchesList != null;
        assertEquals(17, tennisMatchesList.size());
    }

    @Test
    void shouldFindById() {
        int id = 1;
        Optional<TennisMatches> tennisMatchesOptional = restClient.get()
                .uri("/api/matches/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        assert tennisMatchesOptional.isPresent();
        TennisMatches tennisMatches = tennisMatchesOptional.get();
        //1,1,Final,Hard,2024-01-28 19:30:00.000000,03:30:00
        TennisMatches expectedTennisMatch = new TennisMatches(1,1,"Final","Hard", LocalDate.of(2024,1,28), LocalTime.of(3,30,0));
        assertEquals(tennisMatches, expectedTennisMatch);
    }

}
