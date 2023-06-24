package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freelance.models.User;
import com.freelance.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

    static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from users";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from users where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = (new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return user;
    }

    @Override
    public void createUser(User user) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into users(username, password, firstname, lastname) values (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());

            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
    }

}
