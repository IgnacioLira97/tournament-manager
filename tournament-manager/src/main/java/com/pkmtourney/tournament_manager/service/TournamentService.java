package com.pkmtourney.tournament_manager.service;

import com.pkmtourney.tournament_manager.model.Tournament;
import java.util.List;
import java.util.Optional;


public interface TournamentService {
    List<Tournament> getAllTournaments();
    Optional<Tournament> getTournamentById(Integer id);
    Tournament createTournament(Tournament tournament);
    Tournament updateTournament(Integer id, Tournament tournament);
    void deleteTournament(Integer id);
}
