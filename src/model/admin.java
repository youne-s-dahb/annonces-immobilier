package model;

public class admin extends compte {

    admin(int id, String Nom, String prenom, String gmail, String password, String telephone,String role) {
        super(id, Nom, prenom, gmail, password, telephone,role);
    }
    admin(String Nom, String prenom, String gmail, String password, String telephone,String role) {
        super(0, Nom, prenom, gmail, password, telephone,role);
    }

}
