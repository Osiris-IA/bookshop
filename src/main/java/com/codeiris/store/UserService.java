package com.codeiris.store.service;

import com.codeiris.store.modele.User;
import com.codeiris.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service métier pour gérer les utilisateurs.
 * Contient la logique métier liée aux comptes utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // À implémenter dans l'US 1

}
