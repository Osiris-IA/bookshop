package com.codeiris.store.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codeiris.store.modele.CartItem;

import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);
    List<CartItem> findByBookId(Long bookId);

    
}
