package com.ericduncandev.TennisTrackr.TennisGame;


import com.ericduncandev.TennisTrackr.TennisGame.Records.TennisMatches;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(TennisMatchRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TennisMatchRepositoryTest {
    @Autowired
    private TennisMatchRepository tennisMatchRepository;

    @Test
    public void findById() {
        TennisMatches expected = new TennisMatches(1, 1, "Final", "Hard", LocalDate.of(2024, 1, 28), LocalTime.of(3,30,0));
        Optional<TennisMatches> actual = tennisMatchRepository.findById(1);
        assert(actual.isPresent());
        assertEquals(expected, actual.get());

    }

    @Test
    public void findById2() {
        TennisMatches expected = new TennisMatches(2,1,"Semi-Final","Hard",LocalDate.of(2024, 1, 26), LocalTime.of(2,45,0));
        Optional<TennisMatches> actual = tennisMatchRepository.findById(2);
        assert(actual.isPresent());
        assertEquals(expected, actual.get(), "Expect correct id tennis match");

    }
}