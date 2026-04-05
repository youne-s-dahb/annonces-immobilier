package model;

import java.util.Date;

public class Favories {
    private int id_favorie;
    private Date date_ajout;
    private int id_annonce;
    private int id_user;

    //constructeur:
    public Favories(int id_favorie, Date date_ajout, int id_annonce, int id_user){
        this.id_favorie = id_favorie;
        this.date_ajout = date_ajout;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
    }

    //constructeur par params pour insertion: (sans id car id dans la BD est auto-increment)
    public Favories(Date date_ajout, int id_annonce, int id_user){
        this.date_ajout = date_ajout;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
    }

    //Getters:
    public int getId_favorie() {
        return id_favorie;
    }
    public Date getDate_ajout() {
        return date_ajout;
    }
    public int getId_annonce() {
        return id_annonce;
    }
    public int getId_user() {
        return id_user;
    }

    //Setters:
    public void setId_favorie(int id_favorie) {
        this.id_favorie = id_favorie;
    }
    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }
    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
