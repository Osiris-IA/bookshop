package com.codeiris.store.modele;

// import org.checkerframework.checker.units.qual.C;

// import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// entité et accès à la bdd pour les utilisateurs
// ID / email (unique) / mot de passe (hashé) 

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor // Lombok pour générer les getters, setters, constructeurs 

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génère automatiquement l'ID
    private Long id; // ID unique pour chaque utilisateur Long pour les grands nombres 

    @Column(unique = true, nullable = false) // Email doit être unique et non null
    private String email; // Email de l'utilisateur, doit être unique

    @Column(nullable = false) // Mot de passe ne peut pas être null
    private String password; // Mot de passe hashé de l'utilisateur

}
