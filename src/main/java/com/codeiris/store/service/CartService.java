package com.codeiris.store.service;

import org.checkerframework.checker.units.qual.s;

import com.codeiris.store.modele.Book;
import com.codeiris.store.modele.Cart;
import com.codeiris.store.modele.CartItem;
import com.codeiris.store.repository.CartItemRepository;
import com.codeiris.store.repository.CartRepository;
import com.codeiris.store.exception.BookNotFoundException;
import com.codeiris.store.exception.InsufficientStockException;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final BookService bookService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(BookService bookService, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.bookService = bookService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void CheckStockBEforeAddingToCart(Long bookId, int quantity) {
        // récupérer le livre en bdd
        Book book = getBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }

        // Tu regardes sa quantité disponible en stock (book.getQuantityInStock()).
        int availableStock = book.getStock();

        // Comparaison : Si la quantité demandée (ou la nouvelle quantité après
        // modification) est supérieure au stock disponible, tu bloques tout ! Tu lèves
        // une exception personnalisée, par exemple : InsufficientStockException.
        if (quantity > availableStock) {
            throw new InsufficientStockException("Requested quantity exceeds available stock for book ID: " + bookId);
        }

    }

    public CartItem addToCard(Long userId, Long bookId, int quantity) {
        // 1. Récupérer le livre (ou lancer exception si absent)
        Book book = bookService.getBookById(bookId).orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found."));

        // 2. Vérifier stock (ou lancer exception si insuffisant)
        CheckStockBEforeAddingToCart(bookId, quantity);

        // 3. Trouver/créer le panier du user
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart); 
        });
        
        // 4. Vérifier si CartItem existe déjà pour ce panier + livre
        CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book);
        if(cartItem != null) {
            // Si OUI: augmente quantity_existante
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Si NON: crée CartItem
            cartItem = new CartItem(cart, book, quantity); 
        }
        
        // 5. Enregistre/met à jour en base via repository
        cartItemRepository.save(cartItem); 

        // 6. Retourne le CartItem créé/modifié
        return cartItem; 
    }

    public void updateCartItem(Long userId, Long bookId, int quantity) {
        CheckStockBEforeAddingToCart(bookId, quantity);
        // 1. Récupérer le livre
        Book book = bookService.getBookById(bookId).orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found."));

        // 2. Récupérer le panier du user   
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
        // 3. Récupérer le CartItem du panier et du livre
        CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book);
        if (cartItem == null) {
            throw new RuntimeException("Cart item not found for book ID: " + bookId);
        }
        // 2. Vérifier si newQuantity > stock disponible
        if (quantity > book.getStock()) {
            throw new InsufficientStockException("Stock insuffisant pour le livre ID: " + bookId);
        }
    
        // 3. Si c'est bon, mettre à jour la quantité dans le panier
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

}
