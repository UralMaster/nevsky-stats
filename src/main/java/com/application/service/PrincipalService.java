package com.application.service;

import com.application.model.Principal;
import com.application.repository.PrincipalRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrincipalService {

    private final PrincipalRepository principalRepository;

    public PrincipalService(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    public Principal findPrincipalById(UUID uuid) {
        return principalRepository.findById(uuid).get();
    }

    public void savePrincipal(Principal principal) {
        if (principal == null) {
            //TODO: configure logger!
            System.err.println("Principal is null. Are you sure you have connected your form to the application?");
            return;
        }

        principalRepository.save(principal);
    }

}
