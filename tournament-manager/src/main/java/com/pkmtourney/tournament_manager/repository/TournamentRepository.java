package com.pkmtourney.tournament_manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pkmtourney.tournament_manager.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}