package com.codeiris.store.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codeiris.store.exception.EmailAlreadyExistsException;
import com.codeiris.store.repository.UserRepository;
import com.codeiris.store.modele.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateAccount_When_EmailDoesNotAlreadyExist() {
        // Given
        User user = new User(null, "test@example.com", "hashedPassword123");
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(new User(1L, "test@example.com", "hashedPassword123"));

        // When
        User savedUser = userService.createAccount(user);

        // Then (les vérifications)
        assertNotNull(savedUser.getId());
        assertEquals(1L, savedUser.getId());
        assertEquals("test@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void should_ThrowException_When_EmailAlreadyExists() {
        // Given
        User user = new User(null, "test@example.com", "hashedPassword123");
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // When & Then
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createAccount(user));
        verify(userRepository, never()).save(any(User.class));
    }
}
