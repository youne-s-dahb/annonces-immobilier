import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Connexion réussie mriiigla  !");
        } else {
            System.out.println("Connexion échouée🙁 !");

        }
    }
}