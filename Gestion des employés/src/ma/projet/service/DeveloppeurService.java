package ma.projet.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
public class DeveloppeurService implements IDao<Developpeur>{
    @Override
    public boolean create(Developpeur o) {
   try {
            String req = "INSERT into developpeurs (nom, prenom, salair) values(?,?,?)";
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
             ps.setString(2, o.getPrenom());
            ps.setDouble(3, o.getSalaire());
            if (ps.executeUpdate() == 1) {
      return true;
            }
            } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,
            null, ex);
            }
      return false;   }

    @Override
    public boolean update(Developpeur o) {
  try {
         String req = "update developpeurs set nom = ? , prenom = ? , salair = ? where id= ?";
         PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
         ps.setString(1, o.getNom());
         ps.setString(2, o.getPrenom());
          ps.setDouble(3, o.getSalaire());
         ps.setInt(4, o.getId());
        if (ps.executeUpdate() == 1) {
      return true;
        }
        } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
        }
      return false;    
    }

    @Override
    public boolean delete(Developpeur o) {
   if (o == null) {
        System.out.println("Erreur : l'objet Developpeur est nul");
        return false;
    }

    try {
        String req = "delete from developpeurs where id = ?";
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
    public Developpeur getById(int id) {
       
          Developpeur developpeur = null;
    try {
        String req = "SELECT * FROM developpeurs WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Initialiser l'objet Employe avant d'utiliser ses méthodes
            developpeur = new Developpeur(); 
            developpeur.setId(rs.getInt("id"));
            developpeur.setNom(rs.getString("nom"));
            developpeur.setPrenom(rs.getString("prenom"));
            developpeur.setSalaire(rs.getDouble("salaire"));
      
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return developpeur;
    }

    @Override
    public List<Developpeur> getAll() {
     List <Developpeur> developpeurs = new ArrayList<>();
           try {
               String req = "select * from developpeurs ";
               PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
               ResultSet rs = ps.executeQuery();
               while(rs.next())
               developpeurs.add(new Developpeur(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"),
               rs.getDouble("salaire")));
            } catch (SQLException ex) {
                Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
            }
        return developpeurs;}}

  



    

