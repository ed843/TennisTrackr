package com.ericduncandev.TennisTrackr.TennisGame.Records;

import jakarta.validation.constraints.*;

public record TennisMatches(
        Integer matchId,
        @NotNull @Positive
        Integer tournament_id,
        @NotNull @NotBlank
        String round,
        @NotBlank @NotEmpty @Size(min = 3, max = 50)
        String courtSurface,
        java.time.LocalDate matchDate,
        java.time.LocalTime duration
) {
}
