package model;

import java.sql.*;
public class FaavoriesExtendAnnonces extends Annonces{
    private Timestamp dateAjoutFav;
    private int id_favorie;

    public FaavoriesExtendAnnonces(int id, String titre, String desc, double prix, String tel, String type, Timestamp datePub, int idUser, int idVille, int idCat, Timestamp dateAjoutFav,int id_favorie) {
        // Super k-t-3mmer l-ma3loumat d l-Annonce aslya
        super(id, titre, desc, prix, tel, type, datePub, idUser, idVille, idCat);
        this.dateAjoutFav = dateAjoutFav;
        this.id_favorie=id_favorie;
    }
    public FaavoriesExtendAnnonces(String titre, String desc, double prix, String tel, String type, Timestamp datePub, int idUser, int idVille, int idCat, Timestamp dateAjoutFav) {
        // Super k-t-3mmer l-ma3loumat d l-Annonce aslya
        super( titre, desc, prix, tel, type, datePub, idUser, idVille, idCat);
        this.dateAjoutFav = dateAjoutFav;
    }
    public Timestamp getDateAjoutFav() { return dateAjoutFav; };
    public int getIdFavorie() { return id_favorie; }


}
