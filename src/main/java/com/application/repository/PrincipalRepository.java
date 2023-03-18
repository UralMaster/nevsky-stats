package com.application.repository;

import com.application.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrincipalRepository extends JpaRepository<Principal, UUID>  {
}
