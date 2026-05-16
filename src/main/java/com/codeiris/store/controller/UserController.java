package com.codeiris.store.controller;

import com.codeiris.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur REST pour gérer les endpoints utilisateur.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // À implémenter dans l'US 1

}
