package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains main info about {@link Tournament}'s season.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Season extends AbstractEntity {

    public enum Division {
        PLCH_HIGHT ("Высший дивизион ПЛЧ"),
        PLCH_FIRST ("Первый дивизион ПЛЧ"),
        PLCH_SECOND_NORTH ("Второй Северный дивизион ПЛЧ"),
        PLCH_SECOND_SOUTH ("Второй Южный дивизион ПЛЧ"),
        PLCH_THIRD ("Третий дивизион ПЛЧ"),
        PLCH_WINTER_HIGHT ("Зимний Высший дивизион ПЛЧ"),
        PLCH_WINTER_FIRST ("Зимний Первый дивизион ПЛЧ"),
        PLCH_WINTER_SECOND ("Зимний Второй дивизион ПЛЧ"),
        PLCH_WINTER_THIRD ("Зимний Третий дивизион ПЛЧ"),
        PLCH_WINTER_CUP ("Зимний кубок ПЛЧ"),
        PLCH_CL ("Лига чемпионов ПЛЧ"),
        PLCH_EL ("Лига Европы (Кубок) ПЛЧ"),
        PLCH_INTERTOTO ("Кубок Интертото ПЛЧ"),
        PLCH_SUPERCUP ("Суперкубок ПЛЧ"),
        PLCH_QUALIFICATION ("Кубковая квалификация ПЛЧ"),
        PLCH_ONE_DAY ("Однодневный турнир ПЛЧ"),
        PLCH_RED_CUP ("Ред Кап ПЛЧ"),
        NFL ("Невская футбольная лига"),
        FFSPB_CUP_6x6 ("Кубок ФФСПб 6х6"),
        FRIENDLY_GAMES("Товарищеские игры"),
        OTHER ("Другое");

        private final String divisionName;

        Division(String divisionName) {
            this.divisionName = divisionName;
        }

        public String getDivisionName() {
            return divisionName;
        }
    }

    @Enumerated(EnumType.STRING)
    private Division division;

    @NotEmpty
    private String name = "";

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament")
    private Tournament tournament;

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

    public Season() {
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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

