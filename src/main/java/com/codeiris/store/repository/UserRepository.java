package com.codeiris.store.repository;

import com.codeiris.store.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email); 

}
