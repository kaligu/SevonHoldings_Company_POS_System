package lk.sevonholdings.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;

    private final Connection connection;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load the database - driver");
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sevon_Holdings_Distributors", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the database-connection");
        }

    }

    public static DBConnection getDbConnection() {
        return dbConnection==null?dbConnection=new DBConnection():dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
