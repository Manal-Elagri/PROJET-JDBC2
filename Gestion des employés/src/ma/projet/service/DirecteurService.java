package ma.projet.service;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Directeur;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
public class DirecteurService implements IDao<Directeur>{
    
     private ManagerService ms;
     private DeveloppeurService ds;
     
    public DirecteurService() {
    ms = new ManagerService();
    ds = new DeveloppeurService();
    
    }

    @Override
    public boolean create(Directeur o) {      
     try {
        String req = "INSERT INTO directeur (nom, prenom, salair, idD, idM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());
        // Vérification que le développeur et le manager ne sont pas nuls
        if (o.getDev() != null) {
            ps.setInt(4, o.getDev().getId());
        } else {
            ps.setNull(4, Types.INTEGER);
        }
        if (o.getManag() != null) {
            ps.setInt(5, o.getManag().getId());
        } else {
            ps.setNull(5, Types.INTEGER);
        }
        // Affichage des valeurs avant l'exécution
        System.out.println("Requête SQL : " + req);
        System.out.println("Paramètres : " + o.getNom() + ", " + o.getPrenom() + ", " + o.getSalaire() + ", " + 
            (o.getDev() != null ? o.getDev().getId() : "null") + ", " + 
            (o.getManag() != null ? o.getManag().getId() : "null"));
        // Exécution la mise à jour et récupérer les clés générées
        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                o.setId(rs.getInt(1)); 
            }
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Erreur SQL: " + ex.getMessage()); // Afficher l'erreur
    }
    return false;
        
     /*try {
        String req = "INSERT INTO directeur (nom, prenom, salair, idD, idM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDouble(3, o.getSalaire());
         ps.setInt(3, o.getDev().getId());
          ps.setInt(3, o.getManag().getId());

        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                o.setId(rs.getInt(1)); // Récupérer l'ID généré
            }
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;*/
    
    }

    @Override
    public boolean update(Directeur o) {
     try {
         String req = "update directeur set nom = ? , prenom = ? , salair = ? , idD = ? , idM = ? where id= ?";
         PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
         ps.setString(1, o.getNom());
         ps.setString(2, o.getPrenom());
          ps.setDouble(3, o.getSalaire());
           ps.setInt(4, o.getDev().getId());  
        ps.setInt(5, o.getManag().getId());
         ps.setInt(6, o.getId());
        if (ps.executeUpdate() == 1) {
      return true;
        }
        } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
        }
      return false;    
    }

    @Override
    public boolean delete(Directeur o) {
     if (o == null) {
        System.out.println("Erreur : l'objet Directeur est nul");
        return false;
    }
    try {
        String req = "delete from directeur where id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, o.getId());  // L'exception est ici si o est null
        if (ps.executeUpdate() == 1) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }

    @Override
    public Directeur getById(int id) {
    Directeur directeur = null;
            try {
                String req = "select * from directeur where id = ?";
                PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                directeur = new Directeur(rs.getInt("id"),
                rs.getString("nom"), rs.getString("prenom"),
                rs.getDouble("salair"), (Manager) ms.getById(rs.getInt("idM")), 
                (Developpeur) ds.getById(rs.getInt("idD")));                
            }
            } catch (SQLException ex) {
                Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
            }
            return  directeur ; }

    @Override
    public List<Directeur> getAll() {
    List<Directeur> directeurs = new ArrayList<>();
    try {
            String req = "select * from directeur ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            directeurs.add(new Directeur(rs.getInt("id"),
            rs.getString("nom"), rs.getString("prenom"),
            rs.getDouble("salaire"), (Manager) ms.getById(rs.getInt("idM")), 
                    (Developpeur) ds.getById(rs.getInt("idD"))));
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
        }
            return directeurs;
    }

    

    

   
    
}
