package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/rentacar";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123123";
    private static Connection connection = null;


    private Database() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            createConnection(); //Eğer bağlantı null ise bağlantı oluştur
        }
        return connection;
    }


    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DB Kapatma metodu
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
