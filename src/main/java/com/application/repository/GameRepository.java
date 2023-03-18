package com.application.repository;

import com.application.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository  extends JpaRepository<Game, UUID> {

}
