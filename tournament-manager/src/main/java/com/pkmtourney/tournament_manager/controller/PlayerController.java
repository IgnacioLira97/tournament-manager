package com.pkmtourney.tournament_manager.controller;

import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin // allows frontend access (Angular)
public class PlayerController {

    private final PlayerService playerService;
// This class will handle HTTP requests related to players in the tournament
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
// This class will contain methods to manage players in the tournament
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
// This method retrieves all players registered in the tournament
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
// This method retrieves a player by their ID
    @PostMapping
    public Player registerPlayer(@RequestBody Player player) {
        return playerService.registerPlayer(player);
    }
// This method registers a new player in the tournament
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
