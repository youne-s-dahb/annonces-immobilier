import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
    public static Connection getConnection(){

        try {

            Dotenv dotenv = Dotenv.load();

            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String pass = dotenv.get("DB_PASSWORD");

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            return null;
        }

    }
}
