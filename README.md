# socrates-team2

Pour tester : 
Télécharger le JAR sur le repository: 
  Emplacement du JAR : http://nexus-lacombe.westeurope.cloudapp.azure.com:8081/nexus/#welcome , puis aller dans Respositories, Snapshots, et suivre l'arborescence socrates-team2/socrates-team2/1.0-SNAPSHOT et prendre la dernière version disponible.


Pour tester avec la version interactive : 
  - Lancer le jar via l'invite de commande windows en se positionnant dans le répertoire contenant le jar précédemment téléchargé : java -cp <NOM DU JAR>.jar fr.socrates.Main
  - Suivre les instructions à l'écran.
  
Remarques : 
- Aucune vérification de format de saisie n'est effectuée pour l'instant.
- Aucune notion d'ID de participant n'est prise en compte pour l'instant. On peut ajouter un checkin sur un participant qui n'existe pas.



OLD -

Pour la première démo, lancer le main avec les choix suivants :
- Ajout de candidat(s) : java -cp socrates-team2-1.0-SNAPSHOT.jar fr.socrates.CandidateMain john@doe.fr petits@pedestres.lcdv
- Ajout de checkin(s) : java -cp socrates-team2-1.0-SNAPSHOT.jar fr.socrates.CheckinMain john=2017-12-03T23:15:30 houssam=2017-12-03T20:15:30
