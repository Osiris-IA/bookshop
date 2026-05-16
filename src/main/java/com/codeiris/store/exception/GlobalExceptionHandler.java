package com.codeiris.store.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class) 
    
    public ResponseEntity<String> handleEmailConflict(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  
    }
}


// @RestControllerAdvice // Annotation pour indiquer que cette classe gère les // exceptions de manière globale pour les contrôleurs REST

// @ExceptionHandler(EmailAlreadyExistsException.class) // Annotation pour indiquer que cette méthode gère les exceptions de type EmailAlreadyExistsException
    // Retourne une réponse HTTP avec un statut 400 Bad Request et le message d'erreur de l'exception