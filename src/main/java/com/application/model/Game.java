package com.application.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains main info about particular game.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Game extends AbstractEntity {

    public enum HistoricalStatus {
        NEW_AGE("Актуальная"),
        HISTORICAL("Историческая");

        private final String textualStatus;

        HistoricalStatus(String textualStatus) {
            this.textualStatus = textualStatus;
        }

        public String getTextualStatus() {
            return textualStatus;
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nevsky_team")
    private Team nevskyTeam;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opposite_team")
    private Team oppositeTeam;

    @Column(name = "nevsky_goals")
    private int nevskyGoals;
    @Column(name = "opposite_goals")
    private int oppositeGoals;

    private LocalDate gamedate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season")
    private Season season;

    @Column(name = "historical_status")
    @Enumerated(EnumType.STRING)
    private HistoricalStatus historicalStatus;

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

    public Game() {
    }

    public Team getNevskyTeam() {
        return nevskyTeam;
    }

    public void setNevskyTeam(Team nevskyTeam) {
        this.nevskyTeam = nevskyTeam;
    }

    public Team getOppositeTeam() {
        return oppositeTeam;
    }

    public void setOppositeTeam(Team oppositeTeam) {
        this.oppositeTeam = oppositeTeam;
    }

    public int getNevskyGoals() {
        return nevskyGoals;
    }

    public void setNevskyGoals(int nevskyGoals) {
        this.nevskyGoals = nevskyGoals;
    }

    public int getOppositeGoals() {
        return oppositeGoals;
    }

    public void setOppositeGoals(int oppositeGoals) {
        this.oppositeGoals = oppositeGoals;
    }

    public LocalDate getGamedate() {
        return gamedate;
    }

    public void setGamedate(LocalDate gamedate) {
        this.gamedate = gamedate;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public HistoricalStatus getHistoricalStatus() {
        return historicalStatus;
    }

    public void setHistoricalStatus(HistoricalStatus historicalStatus) {
        this.historicalStatus = historicalStatus;
    }
}

