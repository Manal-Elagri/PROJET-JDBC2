package gestion.des.employés;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.*;
import ma.projet.connexion.Connexion;
import ma.projet.service.*;
public class GestionDesEmployés {
    public static void main(String[] args) {

        DeveloppeurService devService = new DeveloppeurService();
        ManagerService managerService = new ManagerService();
        DirecteurService directeurService = new DirecteurService();
           
       // Création de trois développeurs
       Developpeur dev1 = new Developpeur("AMALI", "SALWA", 12547);
       Developpeur dev2 = new Developpeur("SABIHI", "AZIZ", 13560);
       Developpeur dev3 = new Developpeur("NOUARI", "MOHAMMED", 10000);
       devService.create(dev1);
       devService.create(dev2);
       devService.create(dev3);
        
        /*                           Création du Manager                                */
        // Création de développeurs
          dev1.setId(5); 
          dev2.setId(6); 
        // Création de la liste de développeurs
             List<Developpeur> developpeurs = new ArrayList<>();
              developpeurs.add(dev1);
              developpeurs.add(dev2);
         // Création d'un manager
          Manager manager = new Manager("ALAMI", "IDRISSI", 25000);
          // Associer les développeurs au manager
          manager.setDevelop(developpeurs); 
           ManagerService managerService1 = new ManagerService();
           boolean success = managerService1.create(manager);
           System.out.println("Création du manager réussie : " + success);

        /*                          Fin creation manager                                         */
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
                              /*      Création directeur          */
          dev3.setId(7);
          manager.setId(6);  
        // Création d'un directeur
          Directeur directeur = new Directeur("ALAOUI", "MOULAY-HAFID", 70000, manager, dev3);
          directeurService.create(directeur);
        /*                      Fin Création directeur                                       */

                                          /* Affichage des informations    */
        System.out.println("===== Hiérarchie des employés =====");

        // Affichage du manager et des développeurs gérés par le manager
        System.out.println("Manager: " + manager.getNom() + " " 
            + manager.getPrenom() + ", Salaire: " + manager.getSalaire());
        System.out.println("  Développeurs gérés:");
        for (Developpeur dev : developpeurs) {
            System.out.println("    Développeur: " + dev.getNom() + " " 
               + dev.getPrenom() + ", Salaire: " + dev.getSalaire());
        }

        // Affichage du directeur
        System.out.println("Directeur: " + directeur.getNom() + " " 
            + directeur.getPrenom() + ", Salaire: " + directeur.getSalaire());
        System.out.println("  Employés gérés:");
        
        System.out.println("  Développeur (Directeur): " + dev3.getNom() + " " 
            + dev3.getPrenom() + ", Salaire: " + dev3.getSalaire());
        System.out.println("  Manager (Directeur): " + manager.getNom() + " " 
            + manager.getPrenom() + ", Salaire: " + manager.getSalaire());
   
        
        
        
        
        
        
        
        
        
                // Enregistrement des développeurs
//        devService.create(dev1);
        //devService.create(dev2);
        //*************************
     /*   Developpeur dev1 = new Developpeur();
dev1.setId(5); // ou un ID valide
//dev1.setNom("Nom1");

Developpeur dev2 = new Developpeur();
dev2.setId(6); // ou un ID valide
//dev2.setNom("Nom2");

List<Developpeur> developpeurs = new ArrayList<>();
developpeurs.add(dev1);
developpeurs.add(dev2);

          */

        
        
        
   ////////////*
    /*   List<Developpeur> developpeurs = new ArrayList<>();
        developpeurs.add(dev1);
       developpeurs.add(dev2);
        // Création d'un manager pour gérer les deux développeurs
      Manager manager = new Manager("ALAMI", "IDRISSI", 25000);
       managerService.create(manager);
       manager.setDevelop(developpeurs);                     */
        
        
        
        
        
        
                // Récupération du manager après insertion pour obtenir son ID
      //  manager = managerService.getById(1); // Assumons que c'est l'ID auto-incrémenté 1
        
        // Création d'un troisième développeur
       // Developpeur dev8 = new Developpeur("rahil", "fadal", 10000);
//         Manager manager = new Manager("2254", "897", 25000);
      //  dev8.setId(11); // ID valide
          // dev8.setNom("Nom1");
             //manager.setId(4); // ID valide
         //  manager.setNom("Nom1");
       // devService.create(dev8);
        
        // Récupération du troisième développeur après insertion
       // dev3 = devService.getById(3); // Assumons que c'est l'ID auto-incrémenté 3
        
        // Création d'un directeur général qui gère le manager et le 3ème développeur
       // Vérification des IDs
        
        
        
    }
    
}
