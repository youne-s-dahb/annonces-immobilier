package model;

public class Visiteur extends User{
    public Visiteur(int id,String Nom, String prenom, String gmail, String password,String telephone) {
       super(id, Nom, prenom ,password, telephone);
    }
    public Visiteur(String Nom, String prenom, String gmail, String password, String telephone){
        super(0,Nom, prenom, gmail, password, telephone);
    }
}
public void s_inscrire(){
    System.out.println("Le visiteur s'inscrit");

}