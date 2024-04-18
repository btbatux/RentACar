package dao;

import core.Database;
import entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Users> findAll() {     //Tüm user kayıtlarını görüntüle
        ArrayList<Users> userLists = new ArrayList<>();
        String sqlQuery = "SELECT * FROM public.user";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
            while (resultSet.next())
            {

                userLists.add(this.match(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userLists;
    }


    public Users findByLogin(String username, String password) {
        Users obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement prs = this.connection.prepareStatement(query);
            prs.setString(1, username); //1. ?
            prs.setString(2, password); //2. ?
            ResultSet resultSet = prs.executeQuery();
            if (resultSet.next()) // Giriş yapan userin kayıtları bitene kadar dön
            {
                obj = this.match(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Users match(ResultSet rs) throws SQLException {
        Users obj = new Users();
        obj.setId(rs.getInt("user_id")); //Userin kayıtlarını class'a gönderdik.
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }

}
