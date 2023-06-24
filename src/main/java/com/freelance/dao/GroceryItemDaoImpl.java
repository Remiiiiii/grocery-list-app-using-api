package com.freelance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freelance.models.GroceryItem;
import com.freelance.util.ConnectionUtil;

public class GroceryItemDaoImpl implements GroceryItemDao {

    String url = "jdbc:postgresql://database-3.c1id9nh7k0j8.us-east-1.rds.amazonaws.com/grocerylist";
    String username = "postgres";
    String password = "Deeznutspostgresql12#$";

    @Override
    public void markGroceryItemInCart(Integer groceryItemId) {
        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "update grocery_items set in_cart = true where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, groceryItemId);
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GroceryItem> getAllItemsGivenUserId(Integer userId) {

        List<GroceryItem> groceryItems = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from grocery_items where fk_users_id = ? order by id";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                groceryItems.add(
                        new GroceryItem(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groceryItems;
    }

    @Override
    public void createGroceryItem(GroceryItem groceryItem) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "";

            sql = "insert into grocery_items (name, qty, fk_users_id) values (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, groceryItem.getName());
            ps.setInt(2, groceryItem.getQty());
            ps.setInt(3, groceryItem.getUserIdFK());
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroceryItem(Integer groceryItemId) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "delete from grocery_items where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, groceryItemId);
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
