package model;

public class admin extends compte {

    admin(int id, String Nom, String prenom, String gmail, String password, String telephone) {
        super(id, Nom, prenom, gmail, password, telephone);
    }
    admin(String Nom, String prenom, String gmail, String password, String telephone) {
        super(0, Nom, prenom, gmail, password, telephone);
    }

}
