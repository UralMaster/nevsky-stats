package com.application.service;

import com.application.model.Game;
import com.application.model.Participance;
import com.application.model.Player;
import com.application.model.Principal;
import com.application.repository.ParticipanceRepository;
import com.application.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanceService {

    private final ParticipanceRepository participanceRepository;

    private final SecurityService securityService;

    private final PlayerService playerService;

    public ParticipanceService(ParticipanceRepository participanceRepository,
                               SecurityService securityService,
                               PlayerService playerService)
    {
        this.participanceRepository = participanceRepository;
        this.securityService = securityService;
        this.playerService = playerService;
    }

    public List<Participance> findParticipanceByGame(Game game) {
        return participanceRepository.findByGameId(game.getId());
    }

    public void deleteParticipance(Participance participance) {
        Optional<Player> playerOptional = playerService.findPlayerById(participance.getPlayer().getId());
        if (playerOptional.isEmpty()) {
            //TODO: configure logger!
            System.err.println("Player for this participance not exists. Please check consistence of your database");
            return;
        }
        Player player = playerOptional.get();

        String teamName = participance.getGame().getNevskyTeam().getName();

        if ("Невский 26".equals(teamName)) {
            deleteActualN26Participance(participance, player);
        }

        if ("Невский 54".equals(teamName)) {
            deleteActualN54Participance(participance, player);
        }

        playerService.savePlayer(player);
        participanceRepository.delete(participance);
    }

    @Transactional
    public void saveParticipance(Participance participance) {
        if (participance == null) {
            //TODO: configure logger!
            System.err.println("Participance is null. Are you sure you have connected your form to the application?");
            return;
        }

        Optional<Player> playerOptional = playerService.findPlayerById(participance.getPlayer().getId());
        if (playerOptional.isEmpty()) {
            //TODO: configure logger!
            System.err.println("Player for this participance not exists. Please check consistence of your database");
            return;
        }
        Player player = playerOptional.get();

        String teamName = participance.getGame().getNevskyTeam().getName();

        Optional<Participance> oldParticipanceOptional = participanceRepository.findById(participance.getId());

        if (oldParticipanceOptional.isPresent()) {
            //If such participance was created earlier

            Participance oldParticipance = oldParticipanceOptional.get();

            if ("Невский 26".equals(teamName)) {
                updateActualN26Participance(participance, oldParticipance, player);
            }

            if ("Невский 54".equals(teamName)) {
                updateActualN54Participance(participance, oldParticipance, player);
            }

        } else {
            //If this participance is absolutely new

            if ("Невский 26".equals(teamName)) {
                saveNewActualN26Participance(participance, player);
            }

            if ("Невский 54".equals(teamName)) {
                saveNewActualN54Participance(participance, player);
            }

        }

        Principal editor = (Principal) securityService.getAuthenticatedUser();

        player.setEditor(editor);
        player.setEdited(LocalDateTime.now());

        if (participance.getCreator() == null) {
            participance.setCreator(editor);
            participance.setCreated(LocalDateTime.now());
        } else {
            participance.setEditor(editor);
            participance.setEdited(LocalDateTime.now());
        }

        playerService.savePlayer(player);
        participanceRepository.save(participance);

    }

    private void saveNewActualN26Participance(Participance newParticipance,
                                              Player player)
    {
        int goalsDelta = newParticipance.getGoals();
        int assistsDelta = newParticipance.getAssists();
        int yellowCardsDelta = newParticipance.getYellowCards();
        int redCardsDelta = newParticipance.getRedCards();

        player.setGames26(player.getGames26() + 1);
        player.setGoals26(player.getGoals26() + goalsDelta);
        player.setAssists26(player.getAssists26() + assistsDelta);
        player.setYellowCards(player.getYellowCards() + yellowCardsDelta);
        player.setRedCards(player.getRedCards() + redCardsDelta);
    }

    private void saveNewActualN54Participance(Participance newParticipance,
                                              Player player)
    {
        int goalsDelta = newParticipance.getGoals();
        int assistsDelta = newParticipance.getAssists();
        int yellowCardsDelta = newParticipance.getYellowCards();
        int redCardsDelta = newParticipance.getRedCards();

        player.setGames54(player.getGames54() + 1);
        player.setGoals54(player.getGoals54() + goalsDelta);
        player.setAssists54(player.getAssists54() + assistsDelta);
        player.setYellowCards(player.getYellowCards() + yellowCardsDelta);
        player.setRedCards(player.getRedCards() + redCardsDelta);
    }

    private void updateActualN26Participance(Participance newParticipance,
                                             Participance oldParticipance,
                                             Player player)
    {
        int goalsDelta = newParticipance.getGoals() - oldParticipance.getGoals();
        int assistsDelta = newParticipance.getAssists() - oldParticipance.getAssists();
        int yellowCardsDelta = newParticipance.getYellowCards() - oldParticipance.getYellowCards();
        int redCardsDelta = newParticipance.getRedCards() - oldParticipance.getRedCards();

        player.setGoals26(player.getGoals26() + goalsDelta);
        player.setAssists26(player.getAssists26() + assistsDelta);
        player.setYellowCards(player.getYellowCards() + yellowCardsDelta);
        player.setRedCards(player.getRedCards() + redCardsDelta);
    }

    private void updateActualN54Participance(Participance newParticipance,
                                             Participance oldParticipance,
                                             Player player)
    {
        int goalsDelta = newParticipance.getGoals() - oldParticipance.getGoals();
        int assistsDelta = newParticipance.getAssists() - oldParticipance.getAssists();
        int yellowCardsDelta = newParticipance.getYellowCards() - oldParticipance.getYellowCards();
        int redCardsDelta = newParticipance.getRedCards() - oldParticipance.getRedCards();

        player.setGoals54(player.getGoals54() + goalsDelta);
        player.setAssists54(player.getAssists54() + assistsDelta);
        player.setYellowCards(player.getYellowCards() + yellowCardsDelta);
        player.setRedCards(player.getRedCards() + redCardsDelta);
    }

    private void deleteActualN26Participance(Participance participance, Player player) {
        int goalsDelta = participance.getGoals();
        int assistsDelta = participance.getAssists();
        int yellowCardsDelta = participance.getYellowCards();
        int redCardsDelta = participance.getRedCards();

        player.setGames26(player.getGames26() - 1);
        player.setGoals26(player.getGoals26() - goalsDelta);
        player.setAssists26(player.getAssists26() - assistsDelta);
        player.setYellowCards(player.getYellowCards() - yellowCardsDelta);
        player.setRedCards(player.getRedCards() - redCardsDelta);
    }

    private void deleteActualN54Participance(Participance participance, Player player) {
        int goalsDelta = participance.getGoals();
        int assistsDelta = participance.getAssists();
        int yellowCardsDelta = participance.getYellowCards();
        int redCardsDelta = participance.getRedCards();

        player.setGames54(player.getGames54() - 1);
        player.setGoals54(player.getGoals54() - goalsDelta);
        player.setAssists54(player.getAssists54() - assistsDelta);
        player.setYellowCards(player.getYellowCards() - yellowCardsDelta);
        player.setRedCards(player.getRedCards() - redCardsDelta);
    }

}
