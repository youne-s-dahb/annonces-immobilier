package model;

public class compte {

    private int id ;
    private String Nom ;
    private String prenom ;
    private String gmail ;
    private String password ;
    private String telephone ;

    public compte(int id ,String Nom,String prenom,String gmail,String password,String telephone){
        this.id=id;
        this.Nom=Nom;
        this.prenom=prenom;
        this.gmail=gmail;
        this.password=password;
        this.telephone=telephone;
    }

    //---------- GETTER ----------
    public int getId(){
        return this.id;
    }
    public String getNom(){
        return this.Nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public String getGmail(){
        return this.gmail;
    }
    public String getPassword(){
        return this.password;
    }
    public String getTelephone(){
        return this.telephone;
    }
    //---------- SETTER ----------
    public void setId(int id){
        this.id=id;
    }
    public void setNom(String nom){
        this.Nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void getGmail(String gmail){
        this.gmail=gmail;
    }
    public void getPassword(String password){
        this.password=password;
    }
    public void getTelephone(String telephone){
        this.telephone=telephone;
    }
    //------------------------------
}
