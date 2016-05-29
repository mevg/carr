package metificar;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private String DRIVER = "com.mysql.jdbc.Driver";
    private String HOST = "jdbc:mysql://localhost/carr";
    private String USER = "root";
    private String PASS = "root";
    
    Connection connection;
    
    public Conexion() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
