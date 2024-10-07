package ma.projet.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Directeur;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
public class ManagerService implements IDao<Manager>{
    private DeveloppeurService ds;
    public ManagerService() {
    ds = new DeveloppeurService();
    }
    @Override
    public boolean create(Manager o) {
      try {
        // Insértion le manager avec retour de la clé générée
        String req = "INSERT INTO manager (nom, prenom, salair) VALUES (?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());
        // Exécution de la requête et vérification si une ligne a été insérée
        if (ps.executeUpdate() == 1) {
            // Récupération l'ID du manager inséré
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int managerId = rs.getInt(1);
                // Vérification si la liste des développeurs est non nulle et non vide
              List<Developpeur> developpeurs = o.getDevelop();
      if (developpeurs != null && !developpeurs.isEmpty()) {
          for (Developpeur dev : developpeurs) {
        System.out.println("Tentative d'insertion du développeur avec ID: " + dev.getId()); 
        String reqAssociation = "INSERT INTO manager_developpeur (manager_id, developpeur_id) VALUES (?, ?)";
        PreparedStatement psAssociation = Connexion.getConnection().prepareStatement(reqAssociation);
        psAssociation.setInt(1, managerId);
        psAssociation.setInt(2, dev.getId());
        int rowsAffected = psAssociation.executeUpdate();
        System.out.println("Lignes affectées : " + rowsAffected); 
    }}
                return true;
            }}
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    
    
    
    /*    
         try {
        // Insérer le manager
        String req = "INSERT INTO manager (nom, prenom, salair) VALUES (?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());

        if (ps.executeUpdate() == 1) {
            // Récupérer l'ID du manager inséré
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int managerId = rs.getInt(1);

                // Insérer les développeurs dans la table d'association
                for (Developpeur dev : o.getDevelop()) {
                    String reqAssociation= "INSERT INTO manager_developpeur (manager_id, developpeur_id) VALUES (?, ?)";
                    PreparedStatement psAssociation = Connexion.getConnection().prepareStatement(reqAssociation);
                    psAssociation.setInt(1, managerId);
                    psAssociation.setInt(2, dev.getId());
                    psAssociation.executeUpdate();
                }
                return true;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;*/
   /*   try {
        String req = "insert into manager (nom, prenom, salaire, idD) values(?,?,?,?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());
        ps.setInt(4, o.getDevelop().getId());  // Vérification avant ici
       
        if (ps.executeUpdate() == 1) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
*/
    }

    @Override
    public boolean update(Manager o) {
        
         try {
        // Mettre à jour le manager
        String req = "UPDATE manager SET nom = ?, prenom = ?, salair = ? WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());
        ps.setInt(4, o.getId());

        // Vérification si la mise à jour du manager a réussi
        if (ps.executeUpdate() == 1) {
            // Supprimer les anciennes associations dans la table manager_developpeur
            String deleteAssociation = "DELETE FROM manager_developpeur WHERE manager_id = ?";
            PreparedStatement psDelete = Connexion.getConnection().prepareStatement(deleteAssociation);
            psDelete.setInt(1, o.getId());
            psDelete.executeUpdate();

            // Ajout des nouvelles associations
            for (Developpeur dev : o.getDevelop()) {
                String insertAssociation = "INSERT INTO manager_developpeur (manager_id, developpeur_id) VALUES (?, ?)";
                PreparedStatement psInsert = Connexion.getConnection().prepareStatement(insertAssociation);
                psInsert.setInt(1, o.getId());
                psInsert.setInt(2, dev.getId());
                psInsert.executeUpdate();
            }
            return true; 
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; 
        
        
        
  /*   try {
         String req = "update manager set nom = ? , prenom = ? , salair = ? , idD = ? where id= ?";
         PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
         ps.setString(1, o.getNom());
         ps.setString(2, o.getPrenom());
         ps.setDouble(3, o.getSalaire());
         ps.setInt(4, o.getDevelop().getId());  // Vérification avant ici
         ps.setInt(5, o.getId());
        if (ps.executeUpdate() == 1) {
      return true;
        }
        } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE,null, ex);
        }
      return false;    */
    }

    @Override
    public boolean delete(Manager o) {
 
      if (o == null) {
        System.out.println("Erreur : l'objet Directeur est nul");
        return false;
    }

    try {
        String req = "delete from manager where id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, o.getId());  // L'exception est ici si o est null
        if (ps.executeUpdate() == 1) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    
    }

    @Override
    public Manager getById(int id) {
         Manager manager = null;
    try {
        String req = "SELECT * FROM manager WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            manager = new Manager(rs.getInt("id"),
                                  rs.getString("nom"),
                                  rs.getString("prenom"),
                                  rs.getDouble("salair"));
            // Récupération de tous les développeurs associés
            List<Developpeur> developpeurs = getDeveloppeursByManagerId(manager.getId());
            manager.setDevelop(developpeurs); // Assurez-vous d'avoir cette méthode dans la classe Manager
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return manager;
}
     
    // Méthode pour récupérer les développeurs associés à un manager
    private List<Developpeur> getDeveloppeursByManagerId(int managerId) {
    List<Developpeur> developpeurs = new ArrayList<>();
    try {
        String req = "SELECT d.* FROM developpeurs d " +
                     "JOIN manager_developpeur md ON d.id = md.developpeur_id " +
                     "WHERE md.manager_id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            developpeurs.add(new Developpeur(rs.getInt("id"),
                                               rs.getString("nom"),
                                               rs.getString("prenom"),
                                               rs.getDouble("salaire")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return developpeurs;
  /*
     Manager manager = null;
            try {
                String req = "select * from manager where id = ?";
                PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                manager = new Manager(rs.getInt("id"),
                rs.getString("nom"), rs.getString("prenom"),
                rs.getDouble("salair"), (Developpeur) ds.getById(rs.getInt("idD")));
                
            }
            } catch (SQLException ex) {
                Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE,null, ex);
            }
            return  manager ;  */
           
    }

    @Override
    public List<Manager> getAll() {
   List<Manager> managers = new ArrayList<>();
    try {
        String req = "SELECT * FROM manager";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Manager manager = new Manager(rs.getInt("id"),
                                           rs.getString("nom"),
                                           rs.getString("prenom"),
                                           rs.getDouble("salair"));
            // Récupération de tous les développeurs associés
            List<Developpeur> developpeurs = getDeveloppeursByManagerId(manager.getId());
            manager.setDevelop(developpeurs);
            managers.add(manager);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return managers;
  /*   List<Manager> managers = new ArrayList<>();
    try {
            String req = "select * from manager ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            managers.add(new Manager(rs.getInt("id"),
            rs.getString("nom"), rs.getString("prenom"),
            rs.getDouble("salair"), (Developpeur) ds.getById(rs.getInt("idD"))));
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
        }
            return managers;*/
    
    
    
    }

  
    
}
