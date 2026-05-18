package com.codeiris.store.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codeiris.store.modele.Cart;

import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

}
