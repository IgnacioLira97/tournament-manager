package com.pkmtourney.tournament_manager.service;

import com.pkmtourney.tournament_manager.model.Match;

import java.util.List;
import java.util.Optional;


public interface MatchService {
    List<Match> getAllMatches();
    Optional<Match> getMatchById(Integer id);
    Match createMatch(Match match);
    Match updateMatch(Integer id, Match match);
    void deleteMatch(Integer id);
}
