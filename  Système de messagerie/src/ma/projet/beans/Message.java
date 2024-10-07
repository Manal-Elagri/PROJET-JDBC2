package ma.projet.beans;

import java.util.Date;

public class Message {
    
private int id;
private String object;
private String sujet;
private Date date;
private Employe empEmetteur;
private Employe empRecepteur;

public Message(String object, String sujet, Date date, Employe
empEmetteur, Employe empRecepteur) {
this.object = object;
this.sujet = sujet;
this.date = date;
this.empEmetteur = empEmetteur;
this.empRecepteur = empRecepteur;
}
public Message(int id, String object, String sujet, Date date,
Employe empEmetteur, Employe empRecepteur) {
this.id = id;
this.object = object;
this.sujet = sujet;
this.date = date;
this.empEmetteur = empEmetteur;
this.empRecepteur = empRecepteur;
}
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getObject() {
return object;
}
public void setObject(String object) {
this.object = object;
}
public String getSujet() {
return sujet;
}
public void setSujet(String sujet) {
this.sujet = sujet;
}
public Date getDate() {
return date;
}
public void setDate(Date date) {
this.date = date;
}
public Employe getEmpEmetteur() {
return empEmetteur;
}
public void setEmpEmetteur(Employe empEmetteur) {
this.empEmetteur = empEmetteur;
}
public Employe getEmpRecepteur() {
return empRecepteur;
}
public void setEmpRecepteur(Employe empRecepteur) {
this.empRecepteur = empRecepteur;
}   }
