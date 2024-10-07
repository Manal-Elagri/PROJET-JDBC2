package ma.projet.beans;
public class Directeur extends Personne{
    private Manager manag;
    private Developpeur dev;
    public Directeur(  String nom, String prenom, double salaire) {
        super( nom, prenom, salaire);  
    }
    public Directeur(int id, String nom, String prenom, double salaire, Manager manag) {
        super(id, nom, prenom, salaire);
         this.manag = manag;   
    }
  public Directeur(int id, String nom, String prenom, double salaire,  Developpeur dev) {
        super(id, nom, prenom, salaire);
        this.dev = dev;
    }
    public Directeur(int id, String nom, String prenom, double salaire, Manager manag, Developpeur dev) {
        super(id, nom, prenom, salaire);
         this.manag = manag;
        this.dev = dev;
    }
    public Directeur( String nom, String prenom, double salaire, Manager manag, Developpeur dev) {
        super( nom, prenom, salaire);
         this.manag = manag;
        this.dev = dev;
    }
    public Directeur() {
    }
    public Manager getManag() {
        return manag;
    }
    public Developpeur getDev() {
        return dev;
    }
    public void setManag(Manager manag) {
        this.manag = manag;
    }
    public void setDev(Developpeur dev) {
        this.dev = dev;
    }}
