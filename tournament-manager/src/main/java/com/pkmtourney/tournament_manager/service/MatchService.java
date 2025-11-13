package com.pkmtourney.tournament_manager.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pkmtourney.tournament_manager.dto.MatchRequest;
import com.pkmtourney.tournament_manager.model.Match;
import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.model.Tournament;
import com.pkmtourney.tournament_manager.repository.MatchRepository;
import com.pkmtourney.tournament_manager.repository.PlayerRepository;
import com.pkmtourney.tournament_manager.repository.TournamentRepository;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;

    public MatchService(MatchRepository matchRepository, TournamentRepository tournamentRepository,
            PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.playerRepository = playerRepository;
    }

    public Match recordMatch(MatchRequest request) {
        if (request.playerOneId().equals(request.playerTwoId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Players must be different");
        }

        Tournament tournament = tournamentRepository.findById(request.tournamentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
        Player playerOne = findPlayer(request.playerOneId());
        Player playerTwo = findPlayer(request.playerTwoId());

        Match match = new Match();
        match.setTournament(tournament);
        match.setPlayerOne(playerOne);
        match.setPlayerTwo(playerTwo);
        match.setOutcome(request.outcome());
        match.setPlayedAt(LocalDateTime.now());

        return matchRepository.save(match);
    }

    private Player findPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));
    }
}
