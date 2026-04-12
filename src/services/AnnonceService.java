package services;

import database.DBConnection;
import model.Annonces;
import model.User;

import java.sql.*;
import java.util.*;

public class AnnonceService implements annonce{

    // Filtrer les annonces par type et prix
    @Override
    public List<Annonces> filtrer_annonce(String type, double prixMin, double prixMax){
        //list pour stockage des annonce filtré dans une liste vide
        List<Annonces> annc =  new ArrayList<>();
        String sql = "SELECT * FROM annonce WHERE Type=? AND Prix BETWEEN ? AND ? ";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){  // Prépare la requête avec paramètres pour remplire "?"

            stmt.setString(1, type);
            stmt.setDouble(2, prixMin);
            stmt.setDouble(3, prixMax);

            //execute requet sql
            ResultSet res =  stmt.executeQuery();

            while(res.next()){
                //creer objet pour chaque ligne recuperé
                Annonces annonce = new Annonces(res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getDate("Date_publication"),
                        res.getInt("id_user"),
                        res.getInt("id_ville"),
                        res.getInt("id_categorie")
                );
                annc.add(annonce);
            }
        }catch(SQLException e){

            e.printStackTrace(); // kate3tina details dial l'erreur
            System.out.println("Erreur lors de la récupération des annonces. Veuillez réessayer plus tard!!");
        }
        return annc;
    }

    //----------------------------------------------------------------------------------------------------------

    //Consulter annonce par son ID
    @Override
    public List<Annonces> consulter_toutes_annonces_user(int id_user) {
        // Requete SQL bach njibu ga3 les annonces dyal had l-user
        String sql = "SELECT a.*, v.nom_ville FROM annonce a JOIN ville v ON a.id_ville = v.id_ville WHERE a.id_user = ?";

        List<Annonces> listeAnnonces = new ArrayList<>(); // Laye7a fine gha-n-jm3ouhom

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_user);
            ResultSet res = stmt.executeQuery();

            while (res.next()) { // Kan-st3mlo WHILE bach n-douzou 3la kolchi
                Annonces annc = new Annonces(
                        res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getDate("Date_publication"),
                        res.getInt("id_user"),
                        res.getInt("id_ville"),
                        res.getInt("id_categorie")
                );
                listeAnnonces.add(annc); // Kan-zidou l-annonce l-liste
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Impossible de récupérer les annonces.");
        }

        return listeAnnonces; // Kat-rejje3 la liste kamla (t9der t-koun khawya [])
    }

    //----------------------------------------------------------------------------------------------------------

    //chercher annonce par titre/desc
    @Override
    public List<Annonces> chercher_annonce(String search){
        List<Annonces> annc = new ArrayList<>();
        String sql = "SELECT * FROM annonce WHERE Titre LIKE ? OR Description LIKE ? ";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            String recherche = "%" + search + "%";  // % ye3ni ay haja 9bel ou be3d search
            stmt.setString(1, recherche);
            stmt.setString(2, recherche);

            //execute requet sql
            ResultSet res = stmt.executeQuery();

            while(res.next()) {
                //creer objet pour chaque ligne recuperé
                Annonces annonce = new Annonces(
                        res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getDate("Date_publication"),
                        res.getInt("id_user"),
                        res.getInt("id_ville"),
                        res.getInt("id_categorie")

                );
                annc.add(annonce);

            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Impossible de chercher l'annonce pour le moment. Veuillez réessayer plus tard!!");

        }
        return annc;
    }

    //------------------------------------------------------------------------------------------------------------------
    //modifier annonce
    public void modifier_annonce(User userConnecte, Scanner sc,int idAnnonce){
        //Regex:
        String titreRegex = "^[A-Za-z0-9 ]{4,}$";
        String descRegex = "^.{10,}$";
        String telephoneRegex="^[0][5-7][0-9]{8}$";
        String typeRegex = "(?i)^(vente|location)$";

        //Condition + modif Titre
        System.out.print("Modifier le titre de votre annonce: ");
        String titre = sc.nextLine();
        while(!titre.matches(titreRegex)){
            System.out.println("Le titre doit contenir au moins 4 caractères!!");
            System.out.print("Modifier le titre de votre annonce: ");
            titre = sc.nextLine();
        }

        //Condition + modif Description
        System.out.print("Modifier la description de votre annonce: ");
        String desc = sc.nextLine();
        while(!desc.matches(descRegex)){
            System.out.println("La description doit contenir au moins 10 lettres!!");
            System.out.print("Modifier la description de votre annonce: ");
            desc = sc.nextLine();
        }

        //Condition + modif Telephone
        System.out.print("Modifier votre numero de telephone: ");
        String tele = sc.nextLine();
        while(!tele.matches(telephoneRegex)){
            System.out.println("Le numéro doit commencer par 05, 06 ou 07 et contenir 10 chiffres!!");
            System.out.print("Modifier votre numero de telephone: ");
            tele = sc.nextLine();
        }

        //Condition + modif Type
        System.out.print("Modifier le type de votre annonce (Vente/Location): ");
        String type = sc.nextLine();
        while(!type.matches(typeRegex)){
            System.out.println("Vous devez choisir (Vente/Location)!");
            System.out.print("Modifier le type de votre annonce: ");
            type = sc.nextLine();
        }

        //Modifier Prix
        System.out.print("Modifier le prix:");
        double prix = sc.nextDouble();
        sc.nextLine();

        //modifier ville
        System.out.print("Modifier la ville  (1=Oujda,2=Casablanca,3=Rabat,4=Tanger)  : ");
        int idVille = sc.nextInt();

        Annonces annonce = new Annonces();

        //sql update
        String sql = "UPDATE annonce SET Titre=?, Description=?, Prix=?, Telephone=?, Type=?, id_ville=? WHERE id_annonce=?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, titre);
            stmt.setString(2, desc);
            stmt.setDouble(3, prix);
            stmt.setString(4, tele);
            stmt.setString(5, type);
            stmt.setInt(6, idVille);
            stmt.setInt(7, idAnnonce);

            stmt.executeUpdate();

            System.out.println("Annonce modifiée avec succès:)");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erreur lors de la modofication de l'annonce. Veuillez réessayez plus tard.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Supprimer annonces
    public void  Supprimer_Annonces(int id_annonce){
        String Sql="DELETE FROM annonce where id_annonce= ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(Sql))
        {
                stmt.setInt(1,id_annonce);
                int rows=stmt.executeUpdate();
                if(rows>0){
                    System.out.println("✅ Annonces supprimé avec succès !");
                }else{
                    System.out.println("⚠️ Aucun Annonces  trouvé avec cet ID.");
                }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Impossible de supprimer l'annonce actuellement. Veuillez réessayer plus tard");
        }
    }

}




