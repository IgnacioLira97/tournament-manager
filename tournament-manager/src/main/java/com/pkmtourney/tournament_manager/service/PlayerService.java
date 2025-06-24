package com.pkmtourney.tournament_manager.service;

import com.pkmtourney.tournament_manager.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> getAllPlayers();
    Optional<Player> getPlayerById(Integer id);
    Player registerPlayer(Player player);
    Player updatePlayer(Integer id, Player player);
    void deletePlayer(Integer id);
}
