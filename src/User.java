
import java.sql.*;
import java.util.Scanner;

public class User extends Compte {



    User(int id, String Nom, String prenom, String gmail, String password, String telephone) {
        super(id, Nom, prenom, gmail, password, telephone);
    }
    User(String Nom, String prenom, String gmail, String password, String telephone) {
        super(0,Nom, prenom, gmail, password, telephone);
    }

    //---------- Register User ----------
    public void Register_User(String nom, String prenom, String gmail, String password, String confirmPassword, String telephone){
        Scanner sc=new Scanner(System.in);
            //---------- REGEX ----------
            String nomRegex="^[a-zA-Z]{3,}$";
            String prenomRegex="^[a-zA-Z]{3,}$";
            String gmailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            String passwordRegex="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}";
            String telephneRegex="^[0][6-7][0-9]{8}$";

            //---------- Condition ----------
            while(!nom.matches(nomRegex)){
                System.out.println("Nom doit etre supp 3 caracteres");
                nom=sc.nextLine();
            }
            if(!prenom.matches(prenomRegex)){
                System.out.println("Prenom doit etre supp 3 caracteres");
                return;
            }
            if(!password.matches(passwordRegex)){
                System.out.println("Error: Invalid password. Example: Wx1@Abcd");
                return;
            }
            if(!password.equals(confirmPassword)){
                System.out.println("Error: Password et Confirm Password doivent être identiques");
                return;
            }
            if(!gmail.matches(gmailRegex)){
                System.out.println("Error : Gmail incorrect !");
                return;
            }
            if(!telephone.matches(telephneRegex)){
                System.out.println("Error : Telephone incorrect !");
                return;
            }
            //____________________________

            String sql="INSERT INTO `user` (Nom,Prenom,Gmail,Password,Telephone) VALUES (?,?,?,?,?)";
            try(Connection con=DBConnection.getConnection();
                PreparedStatement stmt =con.prepareStatement(sql))
            {
                stmt.setString(2,nom);
                stmt.setString(3,prenom);
                stmt.setString(4,gmail);
                stmt.setString(5,password);
                stmt.setString(6,telephone);

                int rows = stmt.executeUpdate(); //hady kankhdemoha m3a Insert Update DELETE
                if (rows > 0) {
                    System.out.println("User a ete ajouter !!");
                }
            }
            catch(SQLException e){
                if(e.getErrorCode()==1062){ //1062 = Duplicate key ya3ni email deja exist
                    System.out.println("email est deja exist ");
                }else{
                    System.out.println("Error dans db "+e.getMessage());
                }
            }





    }

    //---------- Consulter Profil ----------
    public void  Consulter_profil(int id){
        String sql="SELECT * FROM user WHERE id_user=?";
        try( Connection con=DBConnection.getConnection();
            PreparedStatement stmt =con.prepareStatement(sql))
        {
                stmt.setInt(1, id);//hady katmxy blassa 1 o kadir id li ja mn parametre
                ResultSet res=stmt.executeQuery();

                if(res.next()){
                    System.out.println("--- Profil dialek ---");
                    System.out.println("Nom: " + res.getString("Nom"));
                    System.out.println("Gmail: " + res.getString("Gmail"));
                    System.out.println("Telephone: " + res.getString("Telephone"));

                }else{
                    System.out.println("Ce profil n'est pas reconnue");
                }
        }
        catch (SQLException e){
            System.err.println("Erreur melli bghina Nchoufo Lprofil: " + e.getMessage());
        }
    }

}
