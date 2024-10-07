package ma.projet.beans;
public class Developpeur extends Personne{

    public Developpeur( String nom, String prenom, double salaire) {
        super( nom, prenom, salaire);
    }
     public Developpeur(int id, String nom, String prenom, double salaire) {
        super(id, nom, prenom, salaire);
    }
    public Developpeur() {
    }
}
