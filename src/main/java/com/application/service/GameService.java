package com.application.service;

import com.application.model.Game;
import com.application.model.Principal;
import com.application.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final PrincipalService principalService;

    public GameService(GameRepository gameRepository,
                       PrincipalService principalService)
    {
        this.gameRepository = gameRepository;
        this.principalService = principalService;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public long countGames() {
        return gameRepository.count();
    }

    public void saveGame(Game game) {
        if (game == null) {
            //TODO: configure logger!
            System.err.println("Game is null. Are you sure you have connected your form to the application?");
            return;
        }
        //TODO: save current user for creator or editor
        if (game.getCreator() == null) {
            Principal creator = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            game.setCreator(creator);
            game.setCreated(LocalDateTime.now());
        } else {
            Principal editor = principalService.findPrincipalById(UUID.fromString("89b2bf2e-d6e8-11ec-9d64-0242ac120002"));
            game.setEditor(editor);
            game.setEdited(LocalDateTime.now());
        }

        gameRepository.save(game);
    }

}
