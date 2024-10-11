package com.ericduncandev.TennisTrackr.TennisGame.Records;

public record PlayerStats(
        Integer statId,
        Integer matchId,
        Integer playerId,
        Integer aces,
        Integer doubleFaults,
        Integer firstServesIn,
        Integer firstServesTotal,
        Integer winners,
        Integer unforcedErrors,
        Integer pointsWon

) {

}
