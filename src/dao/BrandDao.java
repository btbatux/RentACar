package dao;

import core.Database;
import entities.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDao {
    private final Connection connection;


    public BrandDao() {
        this.connection = Database.getInstance();
    }


    public ArrayList<Brand> findAll() {     //Tüm brand kayıtlarını görüntüle
        ArrayList<Brand> brandLists = new ArrayList<>();
        String sqlQuery = "SELECT * FROM public.brand ORDER BY brand_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
            while (resultSet.next()) {

                brandLists.add(this.match(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandLists;
    }

    public boolean save(Brand brand) {
        String query = "INSERT INTO public.brand (brand_name) VALUES (?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, brand.getName());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Brand brand) {
        String query = "UPDATE public.brand SET brand_name = ? WHERE brand_id = ?";

        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, brand.getName());
            pr.setInt(2, brand.getId());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Brand getById(int id) {
        Brand obj = new Brand();
        String query = "SELECT * FROM public.brand WHERE brand_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs =  pr.executeQuery();
            if(rs.next())
            {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public Brand match(ResultSet rs) throws SQLException {
        Brand obj = new Brand();
        obj.setId(rs.getInt("brand_id")); //Brand'ın kayıtlarını class'a gönderdik.
        obj.setName(rs.getString("brand_name"));

        return obj;
    }
}
