package com.ericduncandev.TennisTrackr.TennisGame.Records;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Sets(
        @NotNull @Positive
        Integer setId,
        @NotNull @Positive
        Integer matchId,
        @NotNull @Positive @Max(5)
        Integer setNumber,
        @NotNull @PositiveOrZero @Max(7)
        Integer team1Games,
        @NotNull @PositiveOrZero @Max(7)
        Integer team2Games,
        @PositiveOrZero
        Integer team1TiebreakGames,
        @PositiveOrZero
        Integer team2TiebreakGames
) {
}