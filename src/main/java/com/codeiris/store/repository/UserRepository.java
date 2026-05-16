package com.codeiris.store.repository;

import com.codeiris.store.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// méthode pour vérifier si email existe déjà et SB JPA créer la requette SQL automatiquement grâce au nom de la méthode

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//  cette méthode va générer une requête SQL un "SELECT COUNT(*) > 0 FROM users WHERE email = :email" 
    boolean existsByEmail(String email); 

}
