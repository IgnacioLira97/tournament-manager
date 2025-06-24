package com.pkmtourney.tournament_manager.service.impl;
import com.pkmtourney.tournament_manager.model.Tournament;
import com.pkmtourney.tournament_manager.repository.TournamentRepository;
import com.pkmtourney.tournament_manager.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> getTournamentById(Integer id) {
        return tournamentRepository.findById(id);
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament updateTournament(Integer id, Tournament tournament) {
        tournament.setId(id);
        return tournamentRepository.save(tournament);
    }

    @Override
    public void deleteTournament(Integer id) {
        tournamentRepository.deleteById(id);
    }
    
}
