package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static DataBaseManager instance;
    private static final String URI = "jdbc:mysql://localhost:3306/billeterie";
    private static final String USER = "root";
    private static final String PASSWORD = "Aj.auxerre89";
    private DataBaseManager() {

    }
    public static DataBaseManager getInstance() {
        if(instance == null)
            instance = new DataBaseManager();
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URI,USER,PASSWORD);
    }



}
