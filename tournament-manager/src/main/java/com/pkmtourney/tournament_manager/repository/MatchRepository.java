package com.pkmtourney.tournament_manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pkmtourney.tournament_manager.model.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

}