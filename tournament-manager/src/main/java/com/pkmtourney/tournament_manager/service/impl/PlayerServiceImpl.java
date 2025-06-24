package com.pkmtourney.tournament_manager.service.impl;

import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.repository.PlayerRepository;
import com.pkmtourney.tournament_manager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player registerPlayer(Player player) {
        return playerRepository.save(player);
    }


    @Override
    public Player updatePlayer(Integer id, Player player) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id " + id);
        }
        player.setId(id);
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}
