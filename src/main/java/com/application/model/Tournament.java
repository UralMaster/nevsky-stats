package com.application.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Contains main info about tournament.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Tournament extends AbstractEntity {

    @NotEmpty
    private String name = "";

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

    public Tournament() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

