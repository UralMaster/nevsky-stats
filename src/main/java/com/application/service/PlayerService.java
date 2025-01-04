package com.application.service;

import com.application.model.Player;
import com.application.model.Principal;
import com.application.repository.PlayerRepository;
import com.application.security.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service which contains {@link Player}s related logic - search, count, etc.
 *
 * @author Ilya Ryabukhin
 * @since 18.04.2023
 */
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final SecurityService securityService;

    public PlayerService(PlayerRepository playerRepository,
                         SecurityService securityService)
    {
        this.playerRepository = playerRepository;
        this.securityService = securityService;
    }

    public List<Player> findAllPlayers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return playerRepository.findAll();
        } else {
            return playerRepository.search(stringFilter);
        }
    }

    public Optional<Player> findPlayerById(UUID id) {
        return playerRepository.findById(id);
    }

    public long countPlayers() {
        return playerRepository.count();
    }

    public void deletePlayer(Player player) {
        //TODO: implement only logical delete, not physical
        playerRepository.delete(player);
    }

    public void savePlayer(Player player) {
        if (player == null) {
            //TODO: configure logger!
            System.err.println("Player is null. Are you sure you have connected your form to the application?");
            return;
        }

        player.setGames(player.getGames26() + player.getGames54() + player.getGamesFriendly());
        player.setGoals(player.getGoals26() + player.getGoals54() + player.getGoalsFriendly());
        player.setAssists(player.getAssists26() + player.getAssists54() + player.getAssistsFriendly());
        player.setPoints26(player.getGoals26() + player.getAssists26());
        player.setPoints54(player.getGoals54() + player.getAssists54());
        player.setPointsFriendly(player.getGoalsFriendly() + player.getAssistsFriendly());
        player.setPoints(player.getPoints26() + player.getPoints54() + player.getPointsFriendly());

        if (player.getCreator() == null) {
            Principal creator = (Principal) securityService.getAuthenticatedUser();
            player.setCreator(creator);
            player.setCreated(LocalDateTime.now());
        } else {
            Principal editor = (Principal) securityService.getAuthenticatedUser();
            player.setEditor(editor);
            player.setEdited(LocalDateTime.now());
        }

        playerRepository.save(player);
    }

}
