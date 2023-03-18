package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains main info about team.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Team extends AbstractEntity {

    public enum GameSide {
        NEVSKY ("Невский"),
        OTHER ("Соперник");

        private String sideName;

        GameSide(String sideName) {
            this.sideName = sideName;
        }

        public String getSideName() {
            return sideName;
        }

    }

    @NotEmpty
    private String name = "";

    @Enumerated(EnumType.STRING)
    private GameSide side;

    private LocalDate birthday;

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

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameSide getSide() {
        return side;
    }

    public void setSide(GameSide side) {
        this.side = side;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
