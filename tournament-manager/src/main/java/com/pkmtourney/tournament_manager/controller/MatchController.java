package com.pkmtourney.tournament_manager.controller;
import com.pkmtourney.tournament_manager.model.Match;
import com.pkmtourney.tournament_manager.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;


@RestController
@RequestMapping("/matches")
@CrossOrigin // allows frontend access (Angular)
public class MatchController {
    private final MatchService matchService;
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    // This class will handle HTTP requests related to matches in the tournament
    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
    // This method retrieves all matches in the tournament
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable int id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }   

    // This method retrieves a match by its ID
    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }
    // This method creates a new match in the tournament
    @PostMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }       
    // This method updates an existing match in the tournament
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
    // This method deletes a match by its ID



    
}
