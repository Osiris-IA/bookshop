package com.codeiris.store.repository;

import com.codeiris.store.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité User.
 * Gère l'accès à la base de données pour les utilisateurs.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}