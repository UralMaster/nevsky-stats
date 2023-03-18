package com.application.service;

import com.application.model.Principal;
import com.application.model.Team;
import com.application.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final PrincipalService principalService;

    public TeamService(TeamRepository teamRepository,
                       PrincipalService principalService)
    {
        this.teamRepository = teamRepository;
        this.principalService = principalService;
    }

    public List<Team> findAllTeams(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return teamRepository.findAll();
        } else {
            return teamRepository.search(stringFilter);
        }
    }

    public long countTeams() {
        return teamRepository.count();
    }

    public void deleteTeam(Team team) {
        //TODO: implement only logical delete, not physical
        teamRepository.delete(team);
    }

    public void saveTeam(Team team) {
        if (team == null) {
            //TODO: configure logger!
            System.err.println("Team is null. Are you sure you have connected your form to the application?");
            return;
        }
        //TODO: save current user for creator or editor
        if (team.getCreator() == null) {
            Principal creator = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            team.setCreator(creator);
            team.setCreated(LocalDateTime.now());
        } else {
            Principal editor = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            team.setEditor(editor);
            team.setEdited(LocalDateTime.now());
        }

        teamRepository.save(team);
    }

}
