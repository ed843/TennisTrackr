package com.ericduncandev.TennisTrackr.TennisGame.Records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.sql.Date;

public record Tournaments(
        @NotNull @Positive
        Integer tournamentId,
        @NotBlank
        String name,
        String location,
        @PastOrPresent
        Date startDate,
        Date endDate
) {



}
