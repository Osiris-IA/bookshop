# Book Store API

API Spring Boot pour gérer une librairie en ligne.

## Objectif

Ce projet a été réalisé dans le cadre d’un kata. L’objectif est de construire une API REST simple pour gérer les utilisateurs, le catalogue de livres et le panier.

## Stack technique

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Validation
- Gradle
- PostgreSQL
- H2 pour les tests et le développement local
- Docker et docker-compose
- GitHub et GitHub Actions

## Fonctionnalités disponibles

### US1 - Compte utilisateur

- Création de compte avec email unique
- Liste des utilisateurs

### US2 - Catalogue de livres

- Affichage des livres avec pagination
- 10 livres affichés par page
- Recherche par auteur
- Recherche par titre

### US3 - Détails d’un livre

- Affichage complet d’un livre par identifiant

### US4 - Panier

- Ajout d’un livre au panier
- Mise à jour de la quantité d’un livre dans le panier
- Vérification du stock avant ajout

## Architecture

Le code est organisé en 3 couches :

- `controller` : expose les endpoints REST
- `service` : contient la logique métier
- `repository` : accède à la base de données

Les principales entités sont :

- `User`
- `Book`
- `Cart`
- `CartItem`

## Endpoints principaux

- `POST /api/users/register`
- `GET /api/users`
- `GET /api/books`
- `GET /api/books/{id}`
- `GET /api/books/search?author=...`
- `GET /api/books/search/title?title=...`
- `POST /api/cart/items`
- `PUT /api/cart/items/{bookId}`

## Lancer le projet

### Avec Gradle

```bash
./gradlew bootRun
```

### Lancer les tests

```bash
./gradlew test
```

### Avec Docker

Le projet contient un fichier `docker-compose.yml` pour lancer PostgreSQL.

```bash
docker compose up -d
```

## Base de données

- PostgreSQL pour l’application
- H2 pour les tests et certains lancements locaux

## Tests

Le projet contient :

- des tests unitaires sur les services
- des tests d’intégration sur Spring Boot et la base H2

## État du projet

Les user stories 1 à 4 sont implémentées. Les fonctionnalités de paiement et d’historique d’achats restent à compléter.

