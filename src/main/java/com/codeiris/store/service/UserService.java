package com.codeiris.store.service;

import org.springframework.stereotype.Service;
import com.codeiris.store.exception.EmailAlreadyExistsException;
import com.codeiris.store.repository.UserRepository;
import com.codeiris.store.modele.User;


@Service

public class UserService {
    private final UserRepository userRepository; 

    public UserService(UserRepository userRepository) { 
        this.userRepository = userRepository; 
    }

    public User createAccount(User user) {

        if(userRepository.existsByEmail(user.getEmail())
        ) {
            throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail()); 
        }
        return userRepository.save(user); 
    }

}


// Déclaration d'une dépendance vers UserRepository pour accéder aux données des
// utilisateurs final : la référence ne peut pas être modifiée après
// l'initialisation

// Injection par constructeur - Initialisation de la dépendance via le
// constructeur

// Méthode pour créer un compte utilisateur - boucle de validation pour vérifier
// si l'email existe déjà dans la base de données avant de créer un nouvel
// utilisateur
// Lève une exception si l'email existe déjà dans la base de données
// Enregistre le nouvel utilisateur dans la base de données et retourne
// l'utilisateur enregistré