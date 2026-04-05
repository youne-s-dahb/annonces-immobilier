package model;

public class User extends compte{

    public User(int id, String Nom, String prenom, String gmail, String password, String telephone) {
        super(id, Nom, prenom, gmail, password, telephone);
    }
    public User(String Nom, String prenom, String gmail, String password, String telephone) {
        super(0,Nom, prenom, gmail, password, telephone);
    }

}
