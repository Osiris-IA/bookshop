package com.codeiris.store.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);// Appelé lorsque l'email existe déjà dans la base de données super : Appelle le constructeur de la classe parente RuntimeException avec le message d'erreur
    }

}
