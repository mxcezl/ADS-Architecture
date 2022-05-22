# TP ADS Architecture
## Groupe

Ce projet a été réalisé par 4 étudiants de l'INSA Hauts-de-France.
Le groupe est composé de :

- ZOLNIERUCK Maxence
- XU Thierry
- VIDREQUIN Josué
- CONSIGNY Maxime

Tous membres de la promotion FISA 4 Informatique.

## Introduction

Ce projet a pour but de réaliser une maquette architecturale d'un hopital.

Plusieurs modules sont impliqués dans cette maquette, pour la création, suppression, modification et visualisation des données.

Toutes les actions sont enregistrés dans une base de données de log qui permet d'ajouter de la traçabilité aux requêtes.

## Visualisation de l'architecture

![architecture_full](./images/architecture_full.png)

## Les bases de données

Ce projet dispose de deux bases de données : une pour les données patients et une autre pour les traces applicatives.

Les deux bases de données sont des PostgreSQL v12.

### Pour les patients

Cette base de données d'appelle **dbpat** pour DataBase Patients.

Les fichiers sont dans le repertoire [DB_Pat](./DB_Pat/).

La base de données est initialisée avec le script [init.sql](/DB_Pat/init.sql).

Pour lancer le service, il suffit de lancer un invite de commande dans le repertoire [DB_Pat](./DB_Pat/) et d'executer :

> docker compose up -d

### Pour les logs

Cette base de données d'appelle **dblog** pour DataBase Logs.

Les fichiers sont dans le repertoire [DB_Traces](./DB_Traces/).

La base de données est initialisée avec le script [init.sql](/DB_Traces/init.sql).

Pour lancer le service, il suffit de lancer un invite de commande dans le repertoire [DB_Traces](./DB_Traces/) et d'executer :

> docker compose up -d

## Les modules

### Service de création

Ce service permet de générer des fichiers **.hl7** correspondants à la création d'un patient en base de données.

La ressource est exposée via l'url **http://BASE_URL/api/adm** en méthode HTTP POST.

Les fichiers **.hl7** sont générés et stockés dans le dossier [outputs](./wscreate/outputs/).

Chaque demande de création de patient est loggé dans la base de données **DB_Traces**.

### Service de recherche

Ce service permet de chercher un patient via son **nom**, **prénom** ou **date de naissance**.

La ressource est exposée via l'url **http://BASE_URL/api/search** en méthode HTTP GET avec les query params :

- nom
- prenom
- ddn (pour la date de naissance)


Exemples :
- **http://BASE_URL/api/search?nom=VIDREQUIN**
- **http://BASE_URL/api/search?prenom=Thierry**
- **http://BASE_URL/api/search?ddn=21-08-2000**

Le service peut réagir de deux façons différentes :
- Retourner les informations du patient s'il est trouvé en base de données
- Retourner **NO_RESULT** si le patient n'a pas été trouvé

Chaque demande recherche de patient est loggé dans la base de données **DB_Traces**.

### Service de modification & suppression

TODO

## Configuration distante

TODO

## Scripts automatisés

TODO

## Requetes Postman

TODO

## Problèmes rencontrés

TODO

### ZUUL Proxy

TODO

### Docker

TODO

### Mirth Connect

TODO

### GUI

TODO

## Récapitulatif

![architecture_done](./images/architecture_done.png)