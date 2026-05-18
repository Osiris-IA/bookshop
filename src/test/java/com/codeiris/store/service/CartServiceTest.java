package com.codeiris.store.service;

import java.util.List;

import com.codeiris.store.exception.InsufficientStockException;
import com.codeiris.store.modele.Book;
import com.codeiris.store.modele.Cart;
import com.codeiris.store.modele.CartItem;
import com.codeiris.store.repository.CartItemRepository;
import com.codeiris.store.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private CartService cartService;

    @Test
    void shouldAddItemAndSaveCartItem() {
        Long userId = 1L;
        Long bookId = 10L;
        int quantity = 2;

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUserId(userId);

        Book book = new Book(bookId, "Test Book", "Author", null, null, 5, null, null);

        when(bookService.getBookById(bookId)).thenReturn(book);
        when(cartRepository.findByUserId(userId)).thenReturn(List.of(cart));
        when(cartItemRepository.findByCart_Id(cart.getId())).thenReturn(List.of());
        when(cartItemRepository.save(any(CartItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartItem savedItem = cartService.addToCard(userId, bookId, quantity);

        assertNotNull(savedItem);
        assertEquals(quantity, savedItem.getQuantity());
        assertEquals(bookId, savedItem.getBook().getId());
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient() {
        Long bookId = 1L;
        int requestedQuantity = 5;

        when(bookService.getBookById(bookId))
                .thenReturn(new Book(bookId, "Test Book", "Author", null, null, 2, null, null));

        assertThrows(InsufficientStockException.class,
                () -> cartService.CheckStockBeforeAddingToCart(bookId, requestedQuantity));

        verify(cartItemRepository, never()).save(any());
    }

    @Test
    void shouldIncreaseQuantityWhenItemAlreadyExists() {
        Long userId = 1L;
        Long bookId = 1L;
        int initialQuantity = 2;
        int additionalQuantity = 3;

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUserId(userId);

        Book book = new Book(bookId, "Test Book", "Author", null, null, 10, null, null);
        CartItem existingItem = new CartItem();
        existingItem.setId(1L);
        existingItem.setCart(cart);
        existingItem.setBook(book);
        existingItem.setQuantity(initialQuantity);

        when(bookService.getBookById(bookId)).thenReturn(book);
        when(cartRepository.findByUserId(userId)).thenReturn(List.of(cart));
        when(cartItemRepository.findByCart_Id(cart.getId()))
                .thenReturn(List.of())
                .thenReturn(List.of(existingItem));
        when(cartItemRepository.save(any(CartItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        cartService.addToCard(userId, bookId, initialQuantity);
        CartItem updatedItem = cartService.addToCard(userId, bookId, additionalQuantity);

        assertEquals(initialQuantity + additionalQuantity, updatedItem.getQuantity());
        verify(cartItemRepository, times(2)).save(any(CartItem.class));
    }
}