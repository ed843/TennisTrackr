package com.ericduncandev.TennisTrackr.TennisGame.Records;

public record MatchPlayers(
        Integer matchId,
        Integer playerId,
        Integer teamNumber,
        Boolean isWinner
) {
}
