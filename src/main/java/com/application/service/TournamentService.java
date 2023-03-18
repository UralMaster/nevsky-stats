package com.application.service;

import com.application.model.Principal;
import com.application.model.Tournament;
import com.application.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    private final PrincipalService principalService;

    public TournamentService(TournamentRepository tournamentRepository,
                             PrincipalService principalService)
    {
        this.tournamentRepository = tournamentRepository;
        this.principalService = principalService;
    }

    public List<Tournament> findAllTournaments(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return tournamentRepository.findAll();
        } else {
            return tournamentRepository.search(stringFilter);
        }
    }

    public long countTournaments() {
        return tournamentRepository.count();
    }

    public void deleteTournament(Tournament tournament) {
        //TODO: implement only logical delete, not physical
        tournamentRepository.delete(tournament);
    }

    public void saveTournament(Tournament tournament) {
        if (tournament == null) {
            //TODO: configure logger!
            System.err.println("Tournament is null. Are you sure you have connected your form to the application?");
            return;
        }
        //TODO: save current user for creator or editor
        if (tournament.getCreator() == null) {
            Principal creator = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            tournament.setCreator(creator);
            tournament.setCreated(LocalDateTime.now());
        } else {
            Principal editor = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            tournament.setEditor(editor);
            tournament.setEdited(LocalDateTime.now());
        }

        tournamentRepository.save(tournament);
    }

}
