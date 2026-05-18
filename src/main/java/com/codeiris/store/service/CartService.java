package com.codeiris.store.service;

import com.codeiris.store.modele.Book;
import com.codeiris.store.modele.Cart;
import com.codeiris.store.modele.CartItem;
import com.codeiris.store.repository.CartItemRepository;
import com.codeiris.store.repository.CartRepository;
import com.codeiris.store.exception.InsufficientStockException;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final BookService bookService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // Constructeur pour l'injection de dépendances
    public CartService(BookService bookService, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.bookService = bookService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void CheckStockBeforeAddingToCart(Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);

        int availableStock = book.getStock();
        if (quantity > availableStock) {
            throw new InsufficientStockException("Requested quantity exceeds available stock for book ID: " + bookId);
        }

    }

    // Méthode pour ajouter un livre au panier
    
    public CartItem addToCard(Long userId, Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);
        CheckStockBeforeAddingToCart(bookId, quantity);
        Cart cart = getOrCreateCart(userId);

        CartItem existingCartItem = null;
        for (CartItem item : cartItemRepository.findByCart_Id(cart.getId())) {
            if (item.getBook() != null && item.getBook().getId().equals(bookId)) {
                existingCartItem = item;
                break;
            }
        }

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            return cartItemRepository.save(existingCartItem);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long userId, Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);
        if (quantity > book.getStock()) {
            throw new InsufficientStockException("Stock insuffisant pour le livre ID: " + bookId);
        }

        Cart cart = getOrCreateCart(userId);

        CartItem existingCartItem = null;
        for (CartItem item : cartItemRepository.findByCart_Id(cart.getId())) {
            if (item.getBook() != null && item.getBook().getId().equals(bookId)) {
                existingCartItem = item;
                break;
            }
        }

        if (existingCartItem == null) {
            throw new RuntimeException("Cart item not found for book ID: " + bookId);
        }

        if (quantity == 0) {
            cartItemRepository.delete(existingCartItem);
            return existingCartItem;
        }

        existingCartItem.setQuantity(quantity);
        return cartItemRepository.save(existingCartItem);
    }

    Cart getOrCreateCart(Long userId) {
        java.util.List<Cart> carts = cartRepository.findByUserId(userId);
        if (!carts.isEmpty()) {
            return carts.get(0);
        }

        Cart newCart = new Cart();
        newCart.setId(userId);
        newCart.setUserId(userId);
        return cartRepository.save(newCart);
    }


    public Cart getCartByUserId(Long userId) {
        java.util.List<Cart> carts = cartRepository.findByUserId(userId);
        if (carts.isEmpty()) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }
        return carts.get(0);
    }

}
