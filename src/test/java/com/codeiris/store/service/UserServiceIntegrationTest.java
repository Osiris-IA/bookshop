package com.codeiris.store.service;

import com.codeiris.store.exception.EmailAlreadyExistsException;
import com.codeiris.store.modele.User;
import com.codeiris.store.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUserAndSaveInDatabase() {
        // Given
        User user = new User(null, "iris@test.com", "securePassword");

        // When
        User savedUser = userService.createAccount(user);

        // Then
        assertNotNull(savedUser.getId(), "L'ID doit être généré automatiquement");
        assertTrue(userRepository.existsByEmail("iris@test.com"), "L'utilisateur doit exister en base de données");
        assertEquals("iris@test.com", savedUser.getEmail(), "L'email doit être correctement enregistré");
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Given
        User user1 = new User(null, "duplicate@test.com", "password1");
        userService.createAccount(user1);

        User user2 = new User(null, "duplicate@test.com", "password2");

        // When & Then
        assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.createAccount(user2),
                "Une exception doit être levée si l'email existe déjà");
    }

    @Test
    void shouldPersistMultipleUsersCorrectly() {
        // Given
        User user1 = new User(null, "alice@example.com", "password1");
        User user2 = new User(null, "bob@example.com", "password2");

        // When
        User savedUser1 = userService.createAccount(user1);
        User savedUser2 = userService.createAccount(user2);

        // Then
        assertTrue(userRepository.existsByEmail("alice@example.com"), "Alice doit exister en base");
        assertTrue(userRepository.existsByEmail("bob@example.com"), "Bob doit exister en base");
        assertNotEquals(savedUser1.getId(), savedUser2.getId(),
                "Les deux utilisateurs doivent avoir des IDs différents");
    }
}
