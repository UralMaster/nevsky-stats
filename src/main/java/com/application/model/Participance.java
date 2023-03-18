package com.application.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Contains main info about particular player's participance in particular game.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Participance extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game")
    private Game game;

    private int goals;

    private int assists;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private Principal creator;

    private LocalDateTime edited;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor")
    private Principal editor;

    private LocalDateTime removed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remover")
    private Principal remover;

    public Participance() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Principal getCreator() {
        return creator;
    }

    public void setCreator(Principal creator) {
        this.creator = creator;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public Principal getEditor() {
        return editor;
    }

    public void setEditor(Principal editor) {
        this.editor = editor;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }

    public Principal getRemover() {
        return remover;
    }

    public void setRemover(Principal remover) {
        this.remover = remover;
    }

}

