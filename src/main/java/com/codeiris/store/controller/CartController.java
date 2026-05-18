package com.codeiris.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codeiris.store.service.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.codeiris.store.modele.Cart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // POST pour ajouter un livre au panier
    @PostMapping(path = "/items")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest request) {
        cartService.addToCard(request.getUserId(), request.getBookId(), request.getQuantity());
        Cart cart = cartService.getCartByUserId(request.getUserId());
        return ResponseEntity.ok(cart);
    }

    // PUT pour modifier la quantité d'un livre déjà présent
    @PutMapping(path = "/items/{bookId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long bookId, @RequestBody UpdateCartItemRequest request) {
        cartService.updateCartItem(request.getUserId(), bookId, request.getQuantity());
        Cart cart = cartService.getCartByUserId(request.getUserId());
        return ResponseEntity.ok(cart);
    }

    // DTOs pour les requêtes
    public static class AddToCartRequest {
        private Long userId;
        private Long bookId;
        private int quantity;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // DTO pour la mise à jour de la quantité d'un livre dans le panier
    public static class UpdateCartItemRequest {
        private Long userId;
        private int quantity;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
