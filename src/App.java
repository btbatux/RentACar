import core.Database;
import view.LoginView;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        //DAO-(DAL) - Data Access Object (LAYER)
        //Entities - Modeller
        //Business
        //View


        // Veritabanı bağlantısını al
        Connection connection = Database.getInstance();
        LoginView loginView = new LoginView();


    }
}