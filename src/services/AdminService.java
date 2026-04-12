package services;

import database.DBConnection;
import model.Annonces;
import model.User;


import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    AnnonceService annonceService=new AnnonceService();
    private Connection conn;

    public  AdminService(){
        this.conn = DBConnection.getConnection();
    }

    public int CountUser(){
        String sql="SELECT COUNT(*) FROM `user` WHERE role NOT LIKE 'Admin'";
        int TotalUser=0;
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            ResultSet res=stmt.executeQuery();
            if(res.next()){
                // res.getInt(1) katjib reqm li khrej men COUNT(*)
                TotalUser =res.getInt(1);
            }

        }catch (SQLException e){
            System.out.println("Error sql "+e.getMessage());
        }
        return TotalUser;
    }
    public int CountAnnonces(){
        String sql="SELECT COUNT(*) FROM `annonce`";
        int TotalUser=0;
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            ResultSet res=stmt.executeQuery();
            if(res.next()){
                // res.getInt(1) katjib reqm li khrej men COUNT(*)
                TotalUser =res.getInt(1);
            }

        }catch (SQLException e){
            System.out.println("Error sql "+e.getMessage());
        }
        return TotalUser;
    }

    public void ListUser(){
        String Sql="Select * from `user` WHERE role NOT LIKE 'ADMIN' ";
        boolean empty = true;
        try(PreparedStatement stmt=conn.prepareStatement(Sql)){
            ResultSet res=stmt.executeQuery();

            while (res.next()){
                empty=false;
                int id = res.getInt("id_user");
                String nom = res.getString("Nom");
                String prenom = res.getString("Prenom");
                String gmail = res.getString("Gmail");
                String password = res.getString("Password");
                String tel = res.getString("Telephone");

                //__________________________________________________________________________;

                System.out.print("Id : "+id +" | ");
                System.out.print("Nom : "+nom +" | ");
                System.out.print("Prenom : "+prenom +" | ");
                System.out.print("Gmail : "+gmail +" | ");
                System.out.print("Password : "+password +" | ");
                System.out.print("Telephone : "+tel +" | \n");

            };

            if (empty) {
                System.out.println("Aucun utilisateur trouvé.");
            }

        }catch (SQLException e){
            System.out.println("Error sql "+e.getMessage());
        }

    }
    public void ListAnnonce(){
        String Sql="Select * from `annonce` ";
        boolean empty = true;
        try(PreparedStatement stmt=conn.prepareStatement(Sql)){
            ResultSet res=stmt.executeQuery();

            while (res.next()){
                empty=false;
                int id = res.getInt("id_annonce");
                String Titre = res.getString("Titre");
                String Description = res.getString("Description");
                String Prix = res.getString("Prix");
                String Telephone = res.getString("Telephone");
                String Type = res.getString("Type");
                String Date_publication = res.getString("Date_publication");
                String id_ville = res.getString("id_ville");
                String id_categorie = res.getString("id_categorie");

                //__________________________________________________________________________;

                System.out.print("Id : "+id +" | ");
                System.out.print("Titre : "+Titre +" | ");
                System.out.print("Description : "+Description +" | ");
                System.out.print("Prix : "+Prix +" | ");
                System.out.print("Telephone : "+Telephone +" | ");
                System.out.print("Type : "+Type +" | ");
                System.out.print("Date_publication : "+Date_publication +" | ");
                System.out.print("id_ville : "+id_ville +" | ");
                System.out.print("id_categorie : "+id_categorie +" | \n");

            };

            if (empty) {
                System.out.println("Aucun Annonces trouvé.");
            }

        }catch (SQLException e){
            System.out.println("Error sql "+e.getMessage());
        }

    }

    public void SupprimerUser(int id_user){
         String Sql="DELETE FROM user WHERE id_user = ?";
         try(PreparedStatement stmt=conn.prepareStatement(Sql))
         {
             stmt.setInt(1,id_user);
             int row=stmt.executeUpdate();

             if (row > 0) {
                 System.out.println("✅ User a ete  supprimé avec succès !");
             } else {
                 System.out.println("⚠️ Aucun user trouvé avec cet ID.");
             }
         }
         catch (SQLException e){
             System.out.println("Error Sql :"+e.getMessage());
         }
     }




}
