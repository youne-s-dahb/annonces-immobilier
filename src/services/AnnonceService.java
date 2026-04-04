package services;

import database.DBConnection;
import model.Annonces;
import java.sql.*;
import java.util.*;

public class AnnonceService implements annonce{

    // Filtrer les annonces par type et prix
    @Override
    public List<Annonces> filtrer_annonce(String type, double prixMin, double prixMax){
        //list pour stockage des annonce filtré dans une liste vide
        List<Annonces> annc = new ArrayList<>();
        String sql = "SELECT * FROM annonce WHERE Type=? AND Prix BETWEEN ? AND ? ";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){  // Prépare la requête avec paramètres pour remplire "?"

            stmt.setString(1, type);
            stmt.setDouble(2, prixMin);
            stmt.setDouble(3, prixMax);

            //execute requet sql
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                //creer objet pour chaque ligne recuperé
                Annonces annonce = new Annonces(res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getDate("Date_publication"),
                        res.getInt("id_user")
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
    public Annonces consulter_annonce(int id_annonce){
        String sql = "SELECT * FROM annonce WHERE id_annonce = ?";
        Annonces annc = null;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id_annonce);
            ResultSet res = stmt.executeQuery();

            if(res.next()){
                annc = new Annonces(
                        res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getDate("Date_publication"),
                        res.getInt("id_user")
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Impossible de récupérer l'annonce pour le moment. Veuillez réessayer plus tard!!");
        }
        return annc;
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
                        res.getInt("id_user")
                );
                annc.add(annonce);

            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Impossible de chercher l'annonce pour le moment. Veuillez réessayer plus tard!!");

        }
        return annc;
    }

}




