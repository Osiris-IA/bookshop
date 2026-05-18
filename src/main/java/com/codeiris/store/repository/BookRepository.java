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

