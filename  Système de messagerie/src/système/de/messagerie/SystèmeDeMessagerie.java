package système.de.messagerie;
import java.util.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.Message;
import ma.projet.service.EmployeService;
import ma.projet.service.MessageService;

public class SystèmeDeMessagerie {

    public static void main(String[] args) {

        //Teste Employe
    EmployeService es = new EmployeService();
    //Création des employes
     es.create(new Employe("LACHGAR", "Mohamed"));
     es.create(new Employe("RAMI", "ALI"));
     es.create(new Employe("SAFI", "Fatima"));
     es.create(new Employe("Hassani", "Aziza"));
     
     System.out.println("****** La Liste Des l'emploiyé ****** ");  
     for(Employe ep : es.getAll())
    System.out.println("Le nom du l'employé est : "+ep.getNom());  
   
    //Modification d'un employe
    Employe e = es.getById(14);
    if (e != null) {
    e.setNom("ALAOUI");
    e.setPrenom("Manal");
    es.update(e);
    }
    
      System.out.println(" \n");
    System.out.println("******La Liste après la modification du l'emploiyé******");  
     for(Employe emp : es.getAll())
    System.out.println("Le nom du l'employé est : "+emp.getNom());  
    
     
    //Suppression d'un employe
   es.delete(es.getById(14));
    //Liste des employes
      System.out.println(" \n");
      System.out.println("****** La Liste après la suppression du l'emploiyé ****** ");  
  
    for(Employe emp : es.getAll())
    System.out.println("Le nom du l'employé est : "+emp.getNom());  
    
    
    
   


    //Teste Message
    MessageService ms = new MessageService();

    ms.create(new Message("Réunion", "Réunion de fin d'année", new Date(),es.getById(5), es.getById(6)));
    ms.create(new Message("Réunion", "Réunion de fin d'année", new Date(),es.getById(5), es.getById(7)));
    ms.create(new Message("Stage", "Stage à Marrakech", new Date(),es.getById(6), es.getById(5)));
    ms.create(new Message("Stage", "Stage à Marrakech", new Date(),es.getById(6), es.getById(7)));
    //Les message reçus par l'employé 3
     System.out.println(" \n");
     System.out.println("***  Affichage des messages reçus par le 3ème employé **** ");
    for (Message m : ms.getAll()) {
        if(m.getEmpRecepteur().getId() == 7)
           System.out.println("Le sujet de message est  : "+m.getSujet());
    }
    }
    }














