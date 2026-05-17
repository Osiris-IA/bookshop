package com.codeiris.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.codeiris.store.modele.Book;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title); 
}

// Repository pour gérer les opérations de persistance des livres dans la base
// de données.
// Méthode pour trouver des livres par titre, génère une requête SQL "SELECT * FROM books WHERE title = :title"
// JpaRepository fournit des méthodes de base pour les opérations CRUD (Create, Read, Update, Delete) sur les entités Book.