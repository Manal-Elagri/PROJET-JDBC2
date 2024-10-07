# PROJET-JDBC2
# Système de Messagerie d'Entreprise (TP2)

Ce projet implémente un système de messagerie interne entre les employés d'une société. Les employés peuvent envoyer des messages à d'autres employés, et les messages peuvent être stockés dans une base de données MySQL.

## Fonctionnalités

- Envoi de messages entre employés.
- Chaque employé peut envoyer des messages à un ou plusieurs destinataires.
- Consultation des messages reçus par un employé spécifique.
- Gestion des employés et des messages à l'aide des classes `EmployerService` et `MessageService`.
- Utilisation de la base de données MySQL pour stocker les informations.

## Structure du Projet

Le projet comprend les éléments suivants :
- **Classe `Employer`** : représente un employé de l'entreprise.
- **Classe `Message`** : représente un message envoyé entre employés.
- **Interface `IDao`** : définit les méthodes de base pour la gestion des employés et des messages (`create`, `delete`, `update`, `getById`, `getAll`).
- **Classe `EmployerService`** et **Classe `MessageService`** : implémentent les méthodes de l'interface `IDao` pour gérer respectivement les employés et les messages.

## Prérequis

- **JDK 11+**
- **NetBeans 12+**
- **Base de données MySQL**

## Installation

1. Configure la base de données MySQL 'messagerie' avec les scripts SQL ci-dessous :

### Création de la table `employe` :
    ```sql
    CREATE TABLE IF NOT EXISTS `employe` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `nom` varchar(50) NOT NULL,
      `prenom` varchar(50) NOT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=5;
    ```

### Création de la table `message` :
    ````sql
    CREATE TABLE IF NOT EXISTS `message` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `objet` varchar(50) NOT NULL,
      `sujet` varchar(500) NOT NULL,
      `date` date NOT NULL,
      `idE` int(11) NOT NULL,
      `idR` int(11) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idE` (`idE`, `idR`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=18;
    ```

2. Importe le projet dans NetBeans et configure la connexion à la base de données MySQL.

## Utilisation

1. Le premier employé envoie un message aux deux autres.
2. Le deuxième employé envoie un message aux deux autres.
3. Affiche les messages reçus par le troisième employé.

## Test

Pour tester les fonctionnalités :
- Utilise les classes de service (`EmployerService`, `MessageService`) pour gérer les employés et les messages.
- Exécute les méthodes de création, d'envoi de message, et de récupération des messages.

# Gestion des Employés(Travail à Rendre)

Ce projet implémente un système de gestion des employés dans une petite entreprise, mettant en place une hiérarchie comprenant un directeur général, un manager et des développeurs.

## Fonctionnalités

- Création d'une hiérarchie d'employés.
- Affichage des noms et des salaires des employés de haut en bas de la hiérarchie.
- Gestion des employés à l'aide des services correspondants.

## Structure du Projet

Le projet contient les éléments suivants :

- **Classe `Personne`** : classe de base pour les employés.
- **Classe `Directeur`** : hérite de `Personne`, représentant le directeur général.
- **Classe `Manager`** : hérite de `Personne`, représentant le manager qui gère des développeurs.
- **Classe `Developpeur`** : hérite de `Personne`, représentant les développeurs.
- **Interface `IDao`** : définit les méthodes pour la gestion des employés (`create`, `update`, `delete`, `getById`, `getAll`).
- **Classes de Service** : `DirecteurService`, `DeveloppeurService`, et `ManagerService` implémentent l'interface `IDao` pour la gestion des opérations.
- **Classes `Connexion`** : pour établir la connection avec la base de données selon les informations de connexion enregistrer dans le fichier properties.


## Prérequis

- **JDK 11+**
- **NetBeans 12+**
- **Base de données MySQL**

## Installation

- Ouvrir le projet dans NetBeans.
- Lancer xamp.

## Base de Données

### Création de la Base de Données

-Pour créer la base de données `gestion-employé`, utilise les scripts SQL suivants :

```sql
CREATE DATABASE IF NOT EXISTS gestion_employe;
````

-Pour la table `developpeurs` :
````sql
CREATE TABLE IF NOT EXISTS developpeurs (
    id int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(50) NOT NULL,
    prenom varchar(50) NOT NULL,
    salair double NOT NULL,
    PRIMARY KEY (id)                                  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
````

-Pour la table `manager` :
`````sql
CREATE TABLE IF NOT EXISTS manager (
    id int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(50) NOT NULL,
    prenom varchar(50) NOT NULL,
    salair double NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=5;
``````

-Pour la table `directeur` :
``````sql
CREATE TABLE IF NOT EXISTS directeur (
    id int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(50) NOT NULL,
    prenom varchar(50) NOT NULL,
    salair double NOT NULL,
    idD int(11) NOT NULL,
    idm int(11) NOT NULL,
    PRIMARY KEY (id),
    KEY idD (idD),
    KEY idm (idm),
    CONSTRAINT fk_directeur_developpeur_id FOREIGN KEY (idD) REFERENCES developpeurs (id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_directeur_manager_id FOREIGN KEY (idm) REFERENCES manager (id) 
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
``````

-Pour la table `manager-developpeur` :
``````sql
CREATE TABLE IF NOT EXISTS manager_developpeur (
    manager_id INT NOT NULL,
    developpeur_id INT NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES manager(id),
    FOREIGN KEY (developpeur_id) REFERENCES developpeurs(id),
    PRIMARY KEY (manager_id, developpeur_id)
);
``````

## Utilisation

1. La Création de deux développeurs.
2. La Création d'un manager qui gère les deux développeurs.
3. La Création d'un troisième développeur.
4. La Création d'un directeur général qui gère le manager et le troisième développeur.
5. L'affichage des noms et des salaires des employés de haut en bas de la hiérarchie.



