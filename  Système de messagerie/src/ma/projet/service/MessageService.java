package ma.projet.service;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Message;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
public class MessageService implements IDao<Message>{
    
    private EmployeService es;
    public MessageService() {
    es = new EmployeService();
    }
    @Override
    public boolean create(Message o) {
    if (o == null) {
        System.out.println("Erreur : l'objet Message est nul");
        return false;
    }

    if (o.getEmpEmetteur() == null) {
        System.out.println("Erreur : l'employé émetteur est nul");
        return false;
    }

    if (o.getEmpRecepteur() == null) {
        System.out.println("Erreur : l'employé récepteur est nul");
        return false;
    }
    try {
        String req = "insert into message (objet, sujet, date, idE, idR) values(?,?,?,?,?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, o.getObject());
        ps.setString(2, o.getSujet());
        ps.setDate(3, new Date(o.getDate().getTime()));
        ps.setInt(4, o.getEmpEmetteur().getId());  // Vérification avant ici
        ps.setInt(5, o.getEmpRecepteur().getId());
        if (ps.executeUpdate() == 1) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

   /* public boolean create(Message o) {
      try {
          String req = "insert into message (objet, sujet, date, idE,idR) values(?,?,?,?,?)";
          PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
          ps.setString(1, o.getObject());
          ps.setString(2, o.getSujet());
          ps.setDate(3,  new Date(o.getDate().getTime()));
          ps.setInt(4, o.getEmpEmetteur().getId());
          ps.setInt(5, o.getEmpRecepteur().getId());
          if (ps.executeUpdate() == 1) {
          return true;
        }
        } catch (SQLException ex) {
          Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;    
    }*/

    @Override
    public boolean update(Message o) {
    return false;
  }

    @Override
    public boolean delete(Message o) {
   return false;
  }

    @Override
    public Message getById(int id) {
        Message employe = null;
            try {
                String req = "select * from message where id = ?";
                PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                employe = new Message(rs.getInt("id"),
                rs.getString("objet"), rs.getString("sujet"),
                rs.getDate("date"), es.getById(rs.getInt("idE")),
                es.getById(rs.getInt("idR")));
            }
            } catch (SQLException ex) {
                Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE,null, ex);
            }
            return employe;
 }

    @Override
    public List<Message> getAll() {
    List<Message> employes = new ArrayList<>();
    try {
            String req = "select * from message ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            employes.add(new Message(rs.getInt("id"),
            rs.getString("objet"), rs.getString("sujet"),
            rs.getDate("date"), es.getById(rs.getInt("idE")),
            es.getById(rs.getInt("idR"))));
        }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE,null, ex);
        }
            return employes;
 }}



















