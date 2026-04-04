package services;
import database.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class compteService {
        private Connection con;
        public compteService(){
            this.con=DBConnection.getConnection();
        }

        public void Register_User(Scanner sc){
                //---------- REGEX ----------
                String nomRegex="^[a-zA-Z]{3,}$";
                String prenomRegex="^[a-zA-Z]{3,}$";
                String gmailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                String passwordRegex="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}";
                String telephneRegex="^[0][6-7][0-9]{8}$";

                //---------- Condition ----------
                //---------- NOM ----------
                System.out.print("Saisi votre nom :");
                String nom=sc.nextLine();
                while(!nom.matches(nomRegex)){
                    System.out.println("Nom doit etre supp 3 caracteres");
                    System.out.print("Saisi votre nom :");
                    nom=sc.nextLine();
                }
                //---------- PRENOM ----------
                System.out.print("Saisi votre prenom :");
                String prenom=sc.nextLine();
                while(!prenom.matches(prenomRegex)){
                    System.out.println("Prenom doit etre supp 3 caracteres");
                    System.out.print("Saisi votre prenom :");
                    prenom=sc.nextLine();
                }
                //---------- GMAIL ----------
                System.out.print("Saisi votre gmail :");
                String gmail=sc.nextLine();
                while(!gmail.matches(gmailRegex)){
                    System.out.println("Error : Gmail incorrect !");
                    System.out.print("Saisi votre gmail :");
                    gmail=sc.nextLine();
                }
                //---------- PASSWORD ----------
                System.out.print("Saisi votre password :");
                String password=sc.nextLine();
                while(!password.matches(passwordRegex)){
                    System.out.println("Error: Invalid password. Example: Wx1@Abcd");
                    System.out.print("Saisi votre password :");
                    password=sc.nextLine();
                }
                //---------- CONFIRMATION PASSWORD ----------
                System.out.print("Saisi votre confirmation Password :");
                String confirmPassword=sc.nextLine();
                while(!password.equals(confirmPassword)){
                    System.out.println("Error: Password et Confirm Password doivent être identiques");
                    System.out.print("Saisi votre confirmatio Password :");
                    confirmPassword=sc.nextLine();
                }
                //---------- TELEPHONE ----------
                System.out.print("Saisi votre telephone :");
                String telephone=sc.nextLine();
                while(!telephone.matches(telephneRegex)){
                    System.out.println("Error : Telephone incorrect !");
                    System.out.print("Saisi votre telephone :");
                    telephone=sc.nextLine();
                }
                //____________________________

                String sql="INSERT INTO `user` (Nom,Prenom,Gmail,Password,Telephone) VALUES (?,?,?,?,?)";
                try(
                    PreparedStatement stmt =con.prepareStatement(sql))
                {
                    stmt.setString(1,nom);
                    stmt.setString(2,prenom);
                    stmt.setString(3,gmail);
                    stmt.setString(4,password);
                    stmt.setString(5,telephone);

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


        public void Login(String gmail,String password){
            String Sql="SELECT Nom,Password,Gmail FROM user where Gmail = ?";
            try(PreparedStatement stmt =con.prepareStatement(Sql))
            {
                stmt.setString(1,gmail.trim());//.trim() kat7ayed espace

                ResultSet rs =stmt.executeQuery();


                if (rs.next()) {
                    // Hna Login s7i7!
                    String dbPassword = rs.getString("Password");
                    if(dbPassword.equals(password)){
                        String nom = rs.getString("Nom"); // Smiya li 3endek f BD
                        System.out.println("Bonjour  " + nom + ", votre gmail et password et corecct !");
                    }else{
                        System.out.println("Mot de pass Incorrect !");
                    }

                } else {
                    // Hna Login ghalat
                    System.out.println("Email incorrect.");
                }

            }
            catch(SQLException e){
                    System.out.println("Error dans db "+e.getMessage());
            }

        }

}




