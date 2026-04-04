
package services;

import database.DBConnection;

import java.sql.*;


public class compteService {
        private Connection con;
        compteService(){
            this.con=DBConnection.getConnection();
        }

}
