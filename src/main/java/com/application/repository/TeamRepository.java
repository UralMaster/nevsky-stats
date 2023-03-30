package com.application.repository;

import com.application.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * {@link Team} related implementation of Spring {@link JpaRepository}.
 *
 * @author Ilya Ryabukhin
 * @since 15.04.2022
 */
public interface TeamRepository extends JpaRepository<Team, UUID> {

    @Query("select c from Team c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Team> search(@Param("searchTerm") String searchTerm);

}
