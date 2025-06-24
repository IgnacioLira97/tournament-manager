package com.pkmtourney.tournament_manager.controller;
import com.pkmtourney.tournament_manager.model.Tournament;
import com.pkmtourney.tournament_manager.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tournaments")
@CrossOrigin // allows frontend access (Angular)
// This class will handle HTTP requests related to tournaments




public class TournamentController {
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable int id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable int id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable int id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}
