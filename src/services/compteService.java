package services;
import database.DBConnection;
import model.Annonces;
import model.User;

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

        public User Login(String gmail, String password){
            String Sql="SELECT * FROM user where Gmail = ?";
            try(PreparedStatement stmt =con.prepareStatement(Sql))
            {
                stmt.setString(1,gmail.trim());//.trim() kat7ayed espace

                ResultSet rs =stmt.executeQuery();


                if (rs.next()) {
                    // Hna Login s7i7!
                    String dbPassword = rs.getString("Password");
                    if(dbPassword.equals(password)){
                        return new User(
                                rs.getInt("id_user"),
                                rs.getString("Nom"),
                                rs.getString("Prenom"),
                                rs.getString("Gmail"),
                                rs.getString("Password"),
                                rs.getString("Telephone")
                        );
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
            return null; // ila login ghalat

        }

        public void consulterProfil(User user) {
            if (user != null) {
                System.out.println("\n--- VOTRE PROFIL ---");
                System.out.println("Nom      : " + user.getNom());
                System.out.println("Prenom   : " + user.getPrenom());
                System.out.println("Email    : " + user.getGmail());
                System.out.println("Telephone: " + user.getTelephone());
                System.out.println("--------------------\n");
            } else {
                System.out.println("Erreur: Aucun utilisateur connecté.");
            }
        }

        public void modifier_info_perso(User user, Scanner sc) {
        String nomRegex = "^[a-zA-Z]{3,}$";
        String telephneRegex = "^[0][6-7][0-9]{8}$";
        String passwordRegex = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}";

        // 1. Validations
        System.out.print("Saisi votre nouveau Nom : ");
        String newNom = sc.nextLine();
        while (!newNom.matches(nomRegex)) {
            System.out.print("Nom invalide ! Saisi à nouveau : ");
            newNom = sc.nextLine();
        }

        System.out.print("Saisi votre nouveau Prenom : ");
        String newPrenom = sc.nextLine();
        while (!newPrenom.matches(nomRegex)) {
            System.out.print("Prenom invalide ! Saisi à nouveau : ");
            newPrenom = sc.nextLine();
        }

        System.out.print("Saisi votre nouveau Telephone : ");
        String newTelphone = sc.nextLine();
        while (!newTelphone.matches(telephneRegex)) {
            System.out.print("Telephone invalide ! Saisi à nouveau : ");
            newTelphone = sc.nextLine();
        }

        // 2. Verification Password Qdim
        System.out.print("Saisi votre ANCIEN password : ");
        String inputOldPass = sc.nextLine();
        while (!user.getPassword().equals(inputOldPass)) {
            System.out.print("Ancien password incorrect ! Saisi à nouveau : ");
            inputOldPass = sc.nextLine(); // MOUSSA7A7
        }

        // 3. Saisi Password Jdid
        System.out.print("Saisi nouveau Password : ");
        String newPassword = sc.nextLine();
        while (!newPassword.matches(passwordRegex)) {
            System.out.println("Error: Invalid format (Ex: Abcd123@)");
            System.out.print("Saisi nouveau Password : ");
            newPassword = sc.nextLine();
        }

        // 4. UPDATE f l-Base de données (Daba kolchi validé!)
        String sqlUpdate = "UPDATE user SET Nom = ?, Prenom = ?, Password = ?, Telephone = ? WHERE id_user = ?";

        try (PreparedStatement stmUpdate = con.prepareStatement(sqlUpdate)) {
            stmUpdate.setString(1, newNom);
            stmUpdate.setString(2, newPrenom);
            stmUpdate.setString(3, newPassword);
            stmUpdate.setString(4, newTelphone);
            stmUpdate.setInt(5, user.getId());

            int rows = stmUpdate.executeUpdate();
            if (rows > 0) {
                // Update l-objet f l-mémoire
                user.setNom(newNom);
                user.setPrenom(newPrenom);
                user.setPassword(newPassword);
                user.setTelephone(newTelphone);
                System.out.println("Succès: Profil modifié avec succès !");
            } else {
                System.out.println("Aucune modification effectuée ! ");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

        public void publier_annonce(Annonces annonce){
        String sql = "INSERT INTO annonce(Titre, Description, Prix, Telephone, Type, Date_publication, id_user,id_ville,id_categorie) VALUES(?, ?, ?, ?, ?, NOW(), ?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, annonce.getTitre());
            stmt.setString(2, annonce.getDescription());
            stmt.setDouble(3, annonce.getPrix());
            stmt.setString(4, annonce.getTelephone());
            stmt.setString(5, annonce.getType());
            stmt.setInt(6, annonce.getId_user());
            stmt.setInt(7, annonce.getIdVille());
            stmt.setInt(8, annonce.getIdCategorie());

            stmt.executeUpdate();

            System.out.println("Annonce ajouté avec succés.");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erreur lors de publication de lannonce. Veuillez réessayer plus tard!");
        }
    }

}




