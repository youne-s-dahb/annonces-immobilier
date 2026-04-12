package model;

public class compte {

    private int id ;
    private String Nom ;
    private String prenom ;
    private String gmail ;
    private String password ;
    private String telephone ;
    private String role;

    public compte(int id ,String Nom,String prenom,String gmail,String password,String telephone ,String role){
        this.id=id;
        this.Nom=Nom;
        this.prenom=prenom;
        this.gmail=gmail;
        this.password=password;
        this.telephone=telephone;
        this.role=role;
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
    public String getRole(){
        return this.role;
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
    public void setGmail(String gmail){
        this.gmail=gmail;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public void setRole(String role){
        this.role=role;
    }
    //------------------------------
}
