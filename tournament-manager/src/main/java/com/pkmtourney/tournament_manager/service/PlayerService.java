package com.pkmtourney.tournament_manager.service;

import com.pkmtourney.tournament_manager.repository.PlayerRepository;

import jakarta.persistence.metamodel.PluralAttribute;

import com.pkmtourney.tournament_manager.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    // This class will contain methods to manage players in the tournament
    // For example, methods to add, update, delete players, and retrieve player information
    // It will interact with the PlayerRepository to perform these operations

    // Example method to add a player
    // public Player addPlayer(Player player) {
    //     return playerRepository.save(player);
    // }

    // Example method to get all players
    // public List<Player> getAllPlayers() {
    //     return playerRepository.findAll();
    // }
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id);
    }
    
    public Player registerPlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}
