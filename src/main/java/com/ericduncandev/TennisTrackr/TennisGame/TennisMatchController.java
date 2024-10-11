package com.ericduncandev.TennisTrackr.TennisGame;

import com.ericduncandev.TennisTrackr.TennisGame.Exceptions.MatchNotFoundException;
import com.ericduncandev.TennisTrackr.TennisGame.Records.TennisMatches;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class TennisMatchController {

    private final TennisMatchRepository tennisMatchRepository;

    public TennisMatchController(TennisMatchRepository runRepository) {
        this.tennisMatchRepository = runRepository;
    }

    @GetMapping("")
    List<TennisMatches> findAll() {
        return tennisMatchRepository.findAll();
    }
//
    @GetMapping("/{id}")
    TennisMatches findById(@PathVariable int id) {
        Optional<TennisMatches> tennisMatch = tennisMatchRepository.findById(id);
        if (tennisMatch.isEmpty()) {
            throw new MatchNotFoundException();
        }
        return tennisMatch.get();
    }
//
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody TennisMatches tennisMatch) {
        tennisMatchRepository.create( tennisMatch);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody TennisMatches tennisMatch, @PathVariable int id) {
        tennisMatchRepository.update(tennisMatch, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        tennisMatchRepository.delete(id);
    }
}
