package services;

import database.DBConnection;
import model.FaavoriesExtendAnnonces;

import java.sql.*;
import java.util.*;

public class FavoriesServices {

    private Connection con;
    public FavoriesServices(){
        this.con=DBConnection.getConnection();
    }

    public void ajouter_favorie(int Idannonces,int IdUser){

        // Vérifier si l'annonce est déjà en favori
        String sqlCheck = "SELECT COUNT(*) FROM favoris WHERE id_annonce = ? AND id_user = ?";
        try(PreparedStatement stmtCheck = con.prepareStatement(sqlCheck)) {
            stmtCheck.setInt(1, Idannonces);
            stmtCheck.setInt(2, IdUser);

            ResultSet rs = stmtCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("❌ Cette annonce est déjà en favoris!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification. Veuillez réessayer plus tard!");
            return;
        }

        //ajouter annonce au favori

        String Sql="INSERT INTO `favoris` (Date_ajout,id_annonce,id_user) VALUES (NOW(),?,?) ";

        try(
            PreparedStatement stmt=con.prepareStatement(Sql);)
        {
            stmt.setInt(1,Idannonces);
            stmt.setInt(2,IdUser);
            // 3. Execution
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Annonce ajoutée aux favoris ✅ ");
            }

        }
        catch (SQLException e){
            System.out.println("Erreur lors de l'ajout. Veuillez réessayer plus tard!");
        }
    }
    //-----------------------------------------------------------------------------------------------

    public List<FaavoriesExtendAnnonces> Consulter_list_favorie(int idUser){
        List<FaavoriesExtendAnnonces> list = new ArrayList<>();
        String sql="SELECT a.*, f.* FROM annonce a INNER JOIN favoris f ON a.id_annonce = f.id_annonce WHERE f.id_user = ?; ";
        try(PreparedStatement stmt=con.prepareStatement(sql)){

            stmt.setInt(1,idUser);

            ResultSet res=stmt.executeQuery();

            while(res.next()){
                FaavoriesExtendAnnonces fav=new FaavoriesExtendAnnonces(
                        res.getInt("id_annonce"),
                        res.getString("Titre"),
                        res.getString("Description"),
                        res.getDouble("Prix"),
                        res.getString("Telephone"),
                        res.getString("Type"),
                        res.getTimestamp("Date_publication"),
                        res.getInt("id_user"),
                        res.getInt("id_ville"),
                        res.getInt("id_categorie"),
                        res.getTimestamp("Date_ajout"),
                        res.getInt("id_favoris")
                );
                list.add(fav);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors des consultation. Veuillez réessayer plus tard!");
        }
        return list;

    }
    //---------------------------------------------------------------------------------------------------------------

    public void Supprimer_favorie(int id_annonce){

            String Sql="DELETE FROM favoris WHERE id_annonce = ?";
            try(PreparedStatement stmt=con.prepareStatement(Sql))
            {
                stmt.setInt(1,id_annonce);
                int row=stmt.executeUpdate();
                if (row > 0) {
                    System.out.println("✅ Favorie supprimé avec succès !");
                } else {
                    System.out.println("⚠️ Aucun favorie trouvé avec cet ID.");
                }
            }
            catch (SQLException e){
                System.out.println("Error lors de la suppression. Veuillez réessayer plus tard!");
            }
    }

}
