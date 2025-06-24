package com.pkmtourney.tournament_manager.service.impl;

import com.pkmtourney.tournament_manager.model.Match;
import com.pkmtourney.tournament_manager.repository.MatchRepository;
import com.pkmtourney.tournament_manager.service.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    @Override
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match updateMatch(Integer id, Match match) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id " + id);
        }
        match.setId(id);
        return matchRepository.save(match);
    }

    @Override
    public void deleteMatch(Integer id) {
        matchRepository.deleteById(id);
    }
}
