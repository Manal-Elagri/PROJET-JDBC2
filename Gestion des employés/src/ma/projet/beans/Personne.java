package ma.projet.beans;
public class Personne {
    
    public int id ;
    public String nom ;
    public String prenom ;
    public double salaire;
    public Personne() {
    }
    public Personne(int id, String nom, String prenom, double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }
    public Personne( String nom, String prenom, double salaire) {
      
        this.nom = nom;
         this.prenom = prenom;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", salaire=" + salaire + '}';
    }
 
}
