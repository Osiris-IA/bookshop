package com.codeiris.store.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "cart_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(Cart cart2, Book book2, int quantity2strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cartId;
    private Long bookId;
    private int quantity;

    @ManyToOne 
    private Book book; // quel livre ? 

    @ManyToOne
    private Cart cart; // quel panier appartient cette ligne ?


}
