package com.pkmtourney.tournament_manager.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pkmtourney.tournament_manager.dto.PlayerRequest;
import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.repository.PlayerRepository;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player create(PlayerRequest request) {
        Player player = new Player();
        player.setName(request.name().trim());
        return playerRepository.save(player);
    }

    @Transactional(readOnly = true)
    public List<Player> findAll() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
