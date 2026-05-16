package com.codeiris.store.controller;

// reçoit la requete HTTP, interagit avec le service métier pour traiter la requete, et retourne une reponse HTTP appropriée

import com.codeiris.store.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.codeiris.store.modele.User;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.createAccount(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}


// @RequestMapping("/api/users") Définit le chemin de base pour les endpoints
// liés aux utilisateurs
// Déclaration d'une dépendance vers UserService pour accéder à la logique mé
// ier liée aux utilisateurs
// User createdUser = userService.createAccount(user); // Appelle la m
// thode createAccount du servi
// Retourne une réponse HTTP avec le nouvel utilisateur créé et un statut 20
// Created

//