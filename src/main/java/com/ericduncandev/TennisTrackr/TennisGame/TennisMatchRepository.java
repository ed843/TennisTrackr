package com.ericduncandev.TennisTrackr.TennisGame;

import com.ericduncandev.TennisTrackr.TennisGame.Records.TennisMatches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class TennisMatchRepository {
    private static final Logger log = LoggerFactory.getLogger(TennisMatchRepository.class);
    private final JdbcClient jdbcClient;

    public TennisMatchRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<TennisMatches> findAll() {
        return jdbcClient.sql("SELECT * FROM TennisMatches").query(TennisMatches.class).list();
    }

    public Optional<TennisMatches> findById(Integer match_id) {
        return jdbcClient.sql("SELECT * FROM TennisMatches WHERE match_id = :match_id")
                .param("match_id", match_id) // fake error
                .query(TennisMatches.class)
                .optional();
    }

    public void create(TennisMatches tennisMatches) {
        var updated = jdbcClient.sql("INSERT INTO TennisMatches (tournament_id, round, court_surface, match_date, duration) VALUES(?,?,?,?,?)")
                .params(List.of(tennisMatches.tournament_id(), tennisMatches.round(), tennisMatches.courtSurface(), tennisMatches.matchDate(), tennisMatches.duration()))
                .update();

        Assert.state(updated == 1, "Failed to create tennis match");
    }

    public void update(TennisMatches tennisMatches, Integer match_id) {
        var updated = jdbcClient.sql("UPDATE TennisMatches SET tournament_id = ?, round = ?, court_surface = ?, match_date = ?, duration = ? WHERE id = ?")
                .params(List.of(tennisMatches.tournament_id(), tennisMatches.round(), tennisMatches.courtSurface(), tennisMatches.matchDate(), tennisMatches.duration(), match_id))
                .update();

        Assert.state(updated == 1, "Failed to update tennis match");
    }

    public void delete(Integer match_id) {
        var updated = jdbcClient.sql("DELETE FROM TennisMatches WHERE match_id = :match_id")
                .param("match_id", match_id)
                .update();

        Assert.state(updated == 1, "Failed to delete tennis match");
    }

    public int count() { return jdbcClient.sql("SELECT * FROM TennisMatches").query().listOfRows().size(); }

    public void saveAll(List<TennisMatches> tennisMatches) {tennisMatches.stream().forEach(this::create); }

    public List<TennisMatches> findByTournamentId(Integer tournament_id) {
        return jdbcClient.sql("SELECT * FROM TennisMatches WHERE tournament_id = :tournament_id")
                .param("tournament_id", tournament_id)
                .query(TennisMatches.class)
                .list();
    }
}
