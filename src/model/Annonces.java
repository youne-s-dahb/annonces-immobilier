package model;

import java.util.Date;

public class Annonces {

    //Les attributs:
    private int id_annonce;
    private String titre;
    private String description;
    private double prix;
    private String telephone;
    private String type;
    private Date date_publication;
    private int id_user;

    //Getters:
    public int getId_annonce() {
        return id_annonce;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public double getPrix() {
        return prix;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getType() {
        return type;
    }
    public Date getDate_publication() {
        return date_publication;
    }

    public int getId_user() {
        return id_user;
    }

    //Setters:
    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    //constructeur par params:
    public Annonces(int id_annonce, String titre, String description, double prix, String telephone, String type, Date date_publication , int id_user){
        this.id_annonce = id_annonce;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.telephone = telephone;
        this.type = type;
        this.date_publication = date_publication;
        this.id_user = id_user;
    }
}
