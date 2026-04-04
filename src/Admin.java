
public class Admin  extends Compte{

    Admin(int id, String Nom, String prenom, String gmail, String password, String telephone) {
        super(id, Nom, prenom, gmail, password, telephone);
    }
    Admin(String Nom, String prenom, String gmail, String password, String telephone) {
        super(0, Nom, prenom, gmail, password, telephone);
    }

    //public void supprimer_user(int id){

    //}
}
