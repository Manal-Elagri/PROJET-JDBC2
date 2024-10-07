package ma.projet.beans;
import java.util.List;
public class Manager extends Personne{
    private List<Developpeur> develop;  // Liste de développeurs gérés par ce manager
    public Manager(int id, String nom,String prenom, double salaire, List<Developpeur> develop) {
        super(id, nom, prenom, salaire);
        this.develop = develop;
    }
      public Manager( String nom,String prenom, double salaire, List<Developpeur> develop) {
        super( nom, prenom, salaire);
        this.develop = develop;
    }
      public Manager( String nom,String prenom, double salaire) {
        super( nom, prenom, salaire);
   
    }
      public Manager(int id, String nom,String prenom, double salaire) {
        super(id, nom, prenom, salaire);
   
    }
    public Manager() {
    }
    public List<Developpeur> getDevelop() {
        return develop;
    }
    public void setDevelop(List<Developpeur> develop) {
        this.develop = develop;
    }}
