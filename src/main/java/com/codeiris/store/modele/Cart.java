package com.codeiris.store.modele;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    @Id
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

}
