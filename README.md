# TrainingManagment

## Description
**TrainingManagment** est une application web destinée à la gestion des formations en entreprise.  
Elle permet de gérer les sessions de formation, le suivi des participants, les évaluations post-formation et la génération de certificats.

Cette application a été développée dans le cadre de projets professionnels et personnels pour démontrer mes compétences en **Java, Spring Boot, Angular, Docker et CI/CD**.

---

## Technologies utilisées
- **Backend** : Java 17, Spring Boot  
- **Frontend** : Angular 15  
- **Base de données** : MySQL / H2 (selon environnement)  
- **Containerisation** : Docker  
- **CI/CD** : GitHub Actions / Jenkins (si applicable)  

---

## Fonctionnalités principales
- Gestion des formations : création, modification, suppression, affichage  
- Gestion des participants : inscription et suivi  
- Évaluation des participants post-formation  
- Génération de certificats PDF pour les participants  
- Interface utilisateur responsive et intuitive  

---

## Installation et exécution

### Prérequis
- Java 17  
- Maven  
- Node.js et npm  
- Angular CLI  

### Lancer le backend
```bash
cd TrainingManagment/backend
mvn clean install
mvn spring-boot:run
