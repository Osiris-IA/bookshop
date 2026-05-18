## Les principes SOLID

- Single Responsibility Principle (SRP)
  Responsabilité unique : stipule qu'une classe ne doit avoir qu'une seule et unique raison de changer. Il y a donc une séparation stricte des rôles en limitant les éléments à une tâche. Cela permet une meilleure lisibilité et moins de risques.

- Open/Closed Principle (OCP)
  Ouvert/Fermé signifie qu'une classe devrait être ouverte à l'extension mais fermée à la modification. C'est-à-dire que l'on peut ajouter de nouvelles fonctionnalités sans modifier et fragiliser le code.

- Liskov Substitution Principle (LSP)
  C'est le principe selon lequel on peut remplacer un objet de la classe de base par un objet d'une classe dérivée sans altérer le fonctionnement du code.

- Interface Segregation Principle (ISP)
  Ce principe signifie qu'il vaut mieux créer plusieurs interfaces spécifiques à des besoins plutôt qu'une seule grande interface. Cela évite qu'un client soit forcé de dépendre d'une interface dont il n'a pas besoin.

- Dependency Inversion Principle (DIP)
  C'est un principe qui signifie que les modules de haut niveau ne doivent pas dépendre de modules de bas niveau, mais plutôt d'abstractions.

## les éléments les plus important de Clean Code (5 paragraphes)

Les éléments les plus importants sont :

- des noms clairs
  Pour la lisibilité du code, le choix des noms est essentiel pour les variables, les fonctions et les classes. Donc pas de noms abrégés ou cryptés.
  Le nom doit décrire le comportement de l'élément.

- des fonctions courtes
  Une fonction doit être courte et ne faire qu'une seule chose. Elle ne fait que quelques lignes. Cela permet de mieux comprendre le code et de le maintenir.

- des commentaires utiles
  Les commentaires servent à expliquer un choix métier ou une technique rare. Ils doivent être courts et utiles. S'il y a trop de commentaires, c'est que le code est mal écrit.

- DRY (Don't Repeat Yourself)
  Signifie qu'il n'y a qu'une seule source d'information. Cela permet d'éviter les duplications de code.

- Séparer les responsabilités
  Les fonctions et les classes n'ont qu'une seule responsabilité. Cela facilite la maintenance et les tests du code.

- KISS (Keep It Simple Stupid)
  Privilégier la simplicité, c'est-à-dire éviter la complexité et maintenir des solutions simples et directes.

## Les tests unitaires (pourquoi, comment, concepts)

Les tests unitaires servent à valider le comportement d'une partie isolée du code, comme une méthode. Ils permettent d'éviter les régressions.
On utilise le framework JUnit et la bibliothèque Mockito. Pour tester une classe, on l'isole (on coupe sa connexion avec la base de données). On simule le comportement des dépendances en injectant des mocks (des doublures de test).
On peut ainsi organiser les scénarios en « Étant donné que ... Lorsque ... Alors » (Given-When-Then).

Le mock : créer un faux composant pour simuler les réponses d'un vrai objet.
L'assertion `assertEquals` : où l'on vérifie que le résultat obtenu est strictement égal au résultat attendu.

L'isolation : fait d'isoler le test unitaire de la base de données et d'éviter les requêtes sur le réseau réel.

## Les tests d’intégration (pourquoi, comment, concepts)

Les tests d'intégration vérifient que plusieurs composants fonctionnent correctement ensemble.
Ils garantissent que les différentes pièces (controller, repository) peuvent bien communiquer entre elles.

On peut utiliser `@SpringBootTest`, parfois associé à `@AutoConfigureMock`. On simule une requête HTTP qui traverse le controller, appelle le service et interagit avec la base de données dédiée aux tests.
On configure une base de données relationnelle (SQL) H2 en mémoire, qui est initialisée au début des tests puis détruite après leur exécution.

Mocks : outils permettant de simuler des appels d'API REST (GET, POST, PUT...) de manière proche de la réalité, sans démarrer de serveur web complet.

## Qu’est-ce qu’un bean sur Spring.

Un bean est un objet Java pris en charge par le conteneur IoC (Inversion de Contrôle) du framework, ce qui remplace l'instanciation manuelle via le mot-clé `new`.

Pour qu'une classe devienne un bean géré par Spring, on l'annote selon son rôle, par exemple @Repository, @Service, @RestController.

Au démarrage, Spring effectue un scan des packages, repère les annotations, instancie les classes et injecte les beans là où ils sont demandés.

En résumé, un bean est un objet Java : au lieu d'être créé par un développeur avec `new`, c'est Spring qui le crée, le conserve en mémoire et le fournit.
