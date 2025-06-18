package com.pkmtourney.tournament_manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pkmtourney.tournament_manager.model.Player;


public interface PlayerRepository extends JpaRepository<Player, Integer> {

}