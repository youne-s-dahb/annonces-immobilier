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
        String telephneRegex="^[0][5-7][0-9]{8}$";

        //---------- Condition ----------
        //---------- NOM ----------
        System.out.print("Saisir votre nom :");
        String nom=sc.nextLine();
        while(!nom.matches(nomRegex)){
            System.out.println("Le nom doit etre supp à 3 caracteres");
            System.out.print("Saisir votre nom :");
            nom=sc.nextLine();
        }
        //---------- PRENOM ----------
        System.out.print("Saisir votre prenom :");
        String prenom=sc.nextLine();
        while(!prenom.matches(prenomRegex)){
            System.out.println("Le prénom doit etre supp 3 caracteres");
            System.out.print("Saisir votre prenom :");
            prenom=sc.nextLine();
        }
        //---------- GMAIL ----------
        System.out.print("Saisir votre adresse email :");
        String gmail=sc.nextLine();
        while(!gmail.matches(gmailRegex)){
            System.out.println("L'adresse email doit etre ainsi: exemple@gmail.com !");
            System.out.print("Saisir votre adresse email :");
            gmail=sc.nextLine();
        }

        String sqlG = "SELECT COUNT(*) FROM user WHERE Gmail = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlG)){

            boolean emailExist = true;
            while(emailExist) {
                stmt.setString(1, gmail);
                ResultSet res = stmt.executeQuery();

                if(res.next()){
                    int count = res.getInt(1);
                    if(count > 0){
                        System.out.print("Email deja existant, veuillez saisir à nouveau: ");
                        gmail=sc.nextLine();
                    }else{
                        emailExist = false;
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //---------- PASSWORD ----------
        System.out.print("Saisir votre mot de passe :");
        String password=sc.nextLine();
        while(!password.matches(passwordRegex)){
            System.out.println("Error: Invalid password. Example: Wx1@Abcd");
            System.out.print("Saisir votre password :");
            password=sc.nextLine();
        }
        //---------- CONFIRMATION PASSWORD ----------
        System.out.print("Saisir votre mot de passe pour confirmer :");
        String confirmPassword=sc.nextLine();
        while(!password.equals(confirmPassword)){
            System.out.println("Error: mot de pass et Confirm mot de passe doivent être identiques");
            System.out.print("Saisir votre confirmation de mot de passe :");
            confirmPassword=sc.nextLine();
        }
        //---------- TELEPHONE ----------
        System.out.print("Saisir votre numéro de telephone :");
        String telephone=sc.nextLine();
        while(!telephone.matches(telephneRegex)){
            System.out.println("Le numéro de téléphone doit commencer par  05, 06 ou 07 et contenir exactement 10 chiffres!!");
            System.out.print("Saisir votre telephone :");
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
                System.out.println("✅ Utilisateur ajouté avec succés ✅ ");
            }
        }
        catch(SQLException e){
            if(e.getErrorCode()==1062){ //1062 = Duplicate key ya3ni email deja exist
                System.out.println("Email est deja existant ! ");
            }else{
                System.out.println("Erreur lors de l'inscription. Veuillez réessayer plus tard!");
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
                            rs.getString("Telephone"),
                            rs.getString("role")
                    );
                }else{
                    System.out.println("Mot de pass Incorrect !");
                }

            } else {
                // Hna Login ghalat
                System.out.println("Email incorrect !");
            }

        }
        catch(SQLException e){
            System.out.println("Error lors de la connection. Veuillez réessayer plus tard!");
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
            boolean hasAnnonces =false;
            String sql="Select a.*,u.* from annonce a INNER JOIN user u ON a.id_user=u.id_user where a.id_user = ?";
            try(PreparedStatement stmt=con.prepareStatement(sql)){

                stmt.setInt(1,user.getId());
                ResultSet res=stmt.executeQuery();
                System.out.println("\n--- VOS ANNONCES ---");
                while (res.next()){
                    hasAnnonces=true;
                    int id=res.getInt("id_annonce");
                    String titre =     res.getString("titre");
                    String description = res.getString("description");
                    String type = res.getString("type");
                    String telephone = res.getString("telephone");
                    Timestamp date = res.getTimestamp("date_publication");

                    System.out.println("ID               : " + id);
                    System.out.println("Titre            : " + titre);
                    System.out.println("Description      : " + description);
                    System.out.println("Type             : " + type);
                    System.out.println("Telephone        : " + telephone);
                    System.out.println("Date publication : " + date);
                    System.out.println("--------------------\n");
                }
                System.out.println("--------------------\n");
                if(!hasAnnonces){
                    System.out.println("Aucune annonce trouvée !");
                }

            }catch (SQLException e){
                System.out.println("SQL ERROR :" +e.getMessage());
            }


        } else {
            System.out.println("Erreur: Aucun utilisateur connecté.");
        }
    }

    public void modifier_info_perso(User user, Scanner sc) {
        String nomRegex = "^[a-zA-Z]{3,}$";
        String telephneRegex = "^[0][5-7][0-9]{8}$";
        String passwordRegex = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}";

        // 1. Validations
        System.out.print("Saisir votre nouveau nom : ");
        String newNom = sc.nextLine();
        while (!newNom.matches(nomRegex)) {
            System.out.print("Nom invalide ! Veuillez saisir à nouveau : ");
            newNom = sc.nextLine();
        }

        System.out.print("Saisir votre nouveau Prenom : ");
        String newPrenom = sc.nextLine();
        while (!newPrenom.matches(nomRegex)) {
            System.out.print("Prenom invalide ! Veuillez saisir à nouveau : ");
            newPrenom = sc.nextLine();
        }

        System.out.print("Saisir votre nouveau Telephone : ");
        String newTelphone = sc.nextLine();
        while (!newTelphone.matches(telephneRegex)) {
            System.out.print("Telephone invalide ! Veuillez saisir à nouveau : ");
            newTelphone = sc.nextLine();
        }

        // 2. Verification Password 9dim
        System.out.print("Saisir votre ancien password : ");
        String inputOldPass = sc.nextLine();
        while (!user.getPassword().equals(inputOldPass)) {
            System.out.print("Ancien password incorrect ! Veuillez saisir à nouveau : ");
            inputOldPass = sc.nextLine(); // MOUSSA7A7
        }

        // 3. Saisi Password Jdid
        System.out.print("Saisir nouveau mot de passe : ");
        String newPassword = sc.nextLine();
        while (!newPassword.matches(passwordRegex)) {
            System.out.println("Error: Format invalide (Ex: Abcd123@)");
            System.out.print("Veuillez saisir un nouveau mot de passe : ");
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
                System.out.println("✅ Profil modifié avec succès ✅ ");
            } else {
                System.out.println("Aucune modification effectuée ! ");
            }
        } catch (SQLException e) {
            System.out.println("Impossible de modifier ce profil. Veuillez réessayer plus tard! ");
        }
    }

    public void publier_annonce(User userConnecte, Scanner sc){

        //Regex:
        String titreRegex = "^[A-Za-z0-9 ]{4,}$";
        String descRegex = "^.{10,}$";
        String telephoneRegex="^[0][5-7][0-9]{8}$";
        String typeRegex = "(?i)^(vente|location)$"; //(?i): ignore majuscule/miniscule

        //Conditon Titre:
        System.out.print("Saisir le titre de votre annonce: ");
        String titre = sc.nextLine();
        while(!titre.matches(titreRegex)){
            System.out.print("Le titre doit contenir au moin 4 caractères!!\n");
            System.out.print("Saisir le titre de votre annonce: ");
            titre = sc.nextLine();
        }

        //Conditon decsription:
        System.out.print("Saisir la description de votre annonce: ");
        String desc = sc.nextLine();
        while(!desc.matches(descRegex)){
            System.out.print("La description doit contenir au moin 10 lettres!!\n");
            System.out.print("Saisir la description de votre annonce: ");
            desc = sc.nextLine();
        }

        //Conditon telephone:
        System.out.print("Saisir votre numero de telephone: ");
        String tele = sc.nextLine();
        while(!tele.matches(telephoneRegex)){
            System.out.println("Le numéro de téléphone doit commencer par  05, 06 ou 07 et contenir exactement 10 chiffres!!");
            System.out.print("Saisir votre numero de telephone: ");
            tele = sc.nextLine();
        }
        //Conditon Type:
        System.out.print("Choisir le type de votre annonce(Vente/Location): ");
        String type = sc.nextLine();
        while(!type.matches(typeRegex)){
            System.out.print("Vous devez choisir (Vente/Location) :");
            type = sc.nextLine();
        }
        //Prix
        System.out.print("Saisir le prix:");
        while (!sc.hasNextInt()) { // ma7ed input machi ra9m / hasNextInt = check if int
            System.out.println("Vous devez saisir un chiffre!!");
            System.out.print("Saisir votre choix: ");
            sc.next();
        }

        double choice = sc.nextDouble();

        while(choice < 0){
            System.out.println("Vous devez saisir n prix positif!");
            System.out.print("Saisir le prix: ");
            while (!sc.hasNextInt()) { // ma7ed input machi ra9m / hasNextInt = check if int
                System.out.println("Vous devez saisir un chiffre!!");
                System.out.print("Saisir votre choix: ");
                sc.next();
            }
            choice = sc.nextDouble();
        }

        double prix = choice;
        sc.nextLine();

        //ville

        int ville = -1; // -1 ye3ni ville ma kaynach
        while(ville == -1){
            System.out.print("Saisir le nom de votre ville: ");
            String nomVille = sc.nextLine().trim(); //trim kat7yed les espace entre le mots

            //chercher numville flad BD
            String sqlVille = "SELECT id_ville FROM ville WHERE LOWER(nom_ville) = LOWER(?)"; //lower pour accepetr maj et mini

            try(Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlVille)){

                stmt.setString(1, nomVille);


                try(ResultSet res = stmt.executeQuery()){ //had try ferme Resultset bach ma tkonch fuite memoire
                    if(res.next()){
                        ville = res.getInt("id_ville"); //recuperer le num de la ville
                    }else{
                        System.out.println("Ville non trouvée! Veuillez réessayer a nouveau");
                    }
                }

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Ville introuvable!");
            }
        }

        //Categorie
        System.out.println("Choisir le nombre de la catégorie");
        System.out.println("1- Appartement");
        System.out.println("2- Villa");
        System.out.println("3- Terrain");
        System.out.println("4- Bureau");
        System.out.println("5- Magazin");
        System.out.println("6- Maison");
        System.out.println("7- Riad");
        System.out.println("8- Studio");
        System.out.println("9- Plateau");
        System.out.println("10- Entrepot(depot)");
        System.out.println("11- Ferme");
        System.out.print("Saisir votre choix: ");

        int IdCategorie = -1;
        while (IdCategorie < 1 || IdCategorie > 11) {
            while (!sc.hasNextInt()) {  // Vérifier que c'est un chiffre
                System.out.println("Vous devez saisir un chiffre!!");
                System.out.print("Saisir votre choix: ");
                sc.next();
            }
            IdCategorie = sc.nextInt();
            if (IdCategorie < 1 || IdCategorie > 11) {
                System.out.println("Vous devez saisir un chiffre entre (1-11): ");
                System.out.print("Saisir votre choix: ");
                IdCategorie = sc.nextInt();
            }
        }


        // Créer annonce vide et remplir avec setters
        Annonces annonce = new Annonces();
        annonce.setTitre(titre);
        annonce.setDescription(desc);
        annonce.setPrix(prix);
        annonce.setTelephone(tele);
        annonce.setType(type);
        annonce.setId_user(userConnecte.getId()); // id user connecté
        annonce.setIdVille(ville);
        annonce.setIdCategorie(IdCategorie);


        //-------------------------------------------------------------------------------------

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

            System.out.println("✅ Annonce ajouté avec succés ✅ ");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erreur lors de publication de lannonce. Veuillez réessayer plus tard!");
        }
    }

}




