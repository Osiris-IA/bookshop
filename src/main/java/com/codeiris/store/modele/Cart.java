package com.codeiris.store.modele;

import java.util.List;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@Table(name = "carts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class Cart {
    @Id
    private Long id;
    
    @Column(unique = true, nullable = false)
    private Long userId;

    List<CartItem> items;

    @OneToOne
    private User user; // quel utilisateur possède ce panier ?

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems; //liste des lignes du panier

}
