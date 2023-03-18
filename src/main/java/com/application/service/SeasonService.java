package com.application.service;

import com.application.model.Principal;
import com.application.model.Season;
import com.application.repository.SeasonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    private final PrincipalService principalService;

    public SeasonService(SeasonRepository seasonRepository,
                         PrincipalService principalService)
    {
        this.seasonRepository = seasonRepository;
        this.principalService = principalService;
    }

    public List<Season> findAllSeasons(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return seasonRepository.findAll();
        } else {
            return seasonRepository.search(stringFilter);
        }
    }

    public long countSeasons() {
        return seasonRepository.count();
    }

    public void deleteSeason(Season season) {
        //TODO: implement only logical delete, not physical
        seasonRepository.delete(season);
    }

    public void saveSeason(Season season) {
        if (season == null) {
            //TODO: configure logger!
            System.err.println("Season is null. Are you sure you have connected your form to the application?");
            return;
        }

        if (season.getName() == null || season.getName().isEmpty()) {
            season.setName(season.getDivision().getDivisionName());
        }

        //TODO: save current user for creator or editor
        if (season.getCreator() == null) {
            Principal creator = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            season.setCreator(creator);
            season.setCreated(LocalDateTime.now());
        } else {
            Principal editor = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            season.setEditor(editor);
            season.setEdited(LocalDateTime.now());
        }

        seasonRepository.save(season);
    }

}
