package com.pkmtourney.tournament_manager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pkmtourney.tournament_manager.dto.StandingsEntry;
import com.pkmtourney.tournament_manager.dto.TournamentRequest;
import com.pkmtourney.tournament_manager.enums.MatchOutcome;
import com.pkmtourney.tournament_manager.model.Match;
import com.pkmtourney.tournament_manager.model.Player;
import com.pkmtourney.tournament_manager.model.Tournament;
import com.pkmtourney.tournament_manager.repository.MatchRepository;
import com.pkmtourney.tournament_manager.repository.TournamentRepository;

@Service
@Transactional
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    public TournamentService(TournamentRepository tournamentRepository, MatchRepository matchRepository) {
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }

    public Tournament create(TournamentRequest request) {
        Tournament tournament = new Tournament();
        tournament.setName(request.name().trim());
        LocalDate startDate = request.startDate();
        tournament.setStartDate(startDate);
        return tournamentRepository.save(tournament);
    }

    @Transactional(readOnly = true)
    public List<Tournament> findAll() {
        return tournamentRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate", "id"));
    }

    @Transactional(readOnly = true)
    public Tournament getById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
    }

    @Transactional(readOnly = true)
    public List<Match> listMatches(Long tournamentId) {
        ensureTournamentExists(tournamentId);
        return matchRepository.findByTournamentId(tournamentId);
    }

    @Transactional(readOnly = true)
    public List<StandingsEntry> buildStandings(Long tournamentId) {
        ensureTournamentExists(tournamentId);
        List<Match> matches = matchRepository.findByTournamentId(tournamentId);
        Map<Long, MutableStandings> standings = new HashMap<>();

        for (Match match : matches) {
            accumulate(standings, match.getPlayerOne(), match.getOutcome(), true);
            accumulate(standings, match.getPlayerTwo(), match.getOutcome(), false);
        }

        List<StandingsEntry> result = new ArrayList<>();
        for (MutableStandings entry : standings.values()) {
            result.add(entry.toEntry());
        }
        result.sort(Comparator.comparingInt(StandingsEntry::points).reversed()
                .thenComparing(StandingsEntry::wins, Comparator.reverseOrder())
                .thenComparing(StandingsEntry::playerName));
        return result;
    }

    private void ensureTournamentExists(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
        }
    }

    private void accumulate(Map<Long, MutableStandings> standings, Player player, MatchOutcome outcome,
            boolean isPlayerOne) {
        MutableStandings entry = standings.computeIfAbsent(player.getId(),
                key -> new MutableStandings(player.getId(), player.getName()));

        switch (outcome) {
            case PLAYER_ONE_WIN -> {
                if (isPlayerOne) {
                    entry.win();
                } else {
                    entry.loss();
                }
            }
            case PLAYER_TWO_WIN -> {
                if (isPlayerOne) {
                    entry.loss();
                } else {
                    entry.win();
                }
            }
            case TIE -> entry.tie();
        }
    }

    private static class MutableStandings {
        private final Long playerId;
        private final String playerName;
        private int wins;
        private int losses;
        private int ties;

        MutableStandings(Long playerId, String playerName) {
            this.playerId = playerId;
            this.playerName = playerName;
        }

        void win() {
            wins += 1;
        }

        void loss() {
            losses += 1;
        }

        void tie() {
            ties += 1;
        }

        StandingsEntry toEntry() {
            int points = wins * 3 + ties;
            return new StandingsEntry(playerId, playerName, wins, losses, ties, points);
        }
    }
}
