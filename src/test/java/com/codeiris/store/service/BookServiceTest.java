package com.codeiris.store.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codeiris.store.repository.BookRepository;
import com.codeiris.store.modele.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    // Test Quand on t'appelle avec telle demande de page, renvoie une fausse Page
    // contenant une liste de 2 ou 3 faux livres

    @Test
    void ShouldReturnPageOfBooks() {
        Book book1 = new Book(1L, "Book Title 1", "Author 1", null, null, 0, null, null);
        Book book2 = new Book(2L, "Book Title 2", "Author 2", null, null, 0, null, null);
        Book book3 = new Book(3L, "Book Title 3", "Author 3", null, null, 0, null, null);

        // Given
        List<Book> books = List.of(book1, book2, book3);
        Page<Book> page = new PageImpl<>(books);

        // When on moque le repository pour qu'il retourne la page de livres quand on lui demande une page

        when(bookRepository.findAll(any(Pageable.class))).thenReturn(page);


        // Then
        Page<Book> result = bookService.getBooksCatalog(PageRequest.of(0, 10));

        assertEquals(3, result.getTotalElements());
        verify(bookRepository, times(1)).findAll(any(Pageable.class));

    }

}
