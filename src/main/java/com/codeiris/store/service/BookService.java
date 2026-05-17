package com.codeiris.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeiris.store.exception.BookNotFound;
import com.codeiris.store.modele.Book;
import com.codeiris.store.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooksCatalog(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book getBookById(Long id) {
        if (bookRepository.findById(id).isEmpty()) {
            throw new BookNotFound("Book with id " + id + " not found");
        }
        return bookRepository.findById(id).get();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
