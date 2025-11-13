package com.pkmtourney.tournament_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pkmtourney.tournament_manager.dto.StandingsEntry;
import com.pkmtourney.tournament_manager.dto.TournamentRequest;
import com.pkmtourney.tournament_manager.model.Match;
import com.pkmtourney.tournament_manager.model.Tournament;
import com.pkmtourney.tournament_manager.service.TournamentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tournament create(@RequestBody @Valid TournamentRequest request) {
        return tournamentService.create(request);
    }

    @GetMapping
    public List<Tournament> findAll() {
        return tournamentService.findAll();
    }

    @GetMapping("/{id}/matches")
    public List<Match> listMatches(@PathVariable Long id) {
        return tournamentService.listMatches(id);
    }

    @GetMapping("/{id}/standings")
    public List<StandingsEntry> standings(@PathVariable Long id) {
        return tournamentService.buildStandings(id);
    }
}
