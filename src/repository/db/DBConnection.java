package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/TaxProject",
                    "postgres",
                    "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}