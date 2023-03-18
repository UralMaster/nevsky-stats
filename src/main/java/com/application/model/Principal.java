package com.application.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

/**
 * Contains main info about authenticated application user.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
@Entity
public class Principal extends AbstractEntity {

    @NotEmpty
    private String name;

    public Principal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
