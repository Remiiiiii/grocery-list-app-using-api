// package com.freelance.dao;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import com.freelance.models.User;

// // import com.freelance.store.Store;

// public class UserDaoJavaImpl implements UserDao {

// String url = System.getenv("URL");
// String username = "postgres";
// String password = "Deeznutspostgressql";

// @Override
// public List<User> getAllUsers() {
// List<User> users = new ArrayList<>();

// try {
// Connection conn = DriverManager.getConnection(url, username, password);

// String sql = "select * from users";

// PreparedStatement ps = conn.prepareStatement(sql);

// ResultSet rs = ps.executeQuery();

// while (rs.next()) {
// users.add(new User(
// rs.getInt(1),
// rs.getString(2),
// rs.getString(3),
// rs.getString(4),
// rs.getString(5)));
// }

// conn.close();

// } catch (SQLException e) {
// e.printStackTrace();
// }
// return users;
// }

// @Override
// public User getUserByUsername(String username) {
// throw new UnsupportedOperationException("Unimplemented method
// 'getUserByUsername'");
// }

// @Override
// public void createUser(User user) {
// throw new UnsupportedOperationException("Unimplemented method 'createUser'");
// }

// // @Override
// // public List<User> getAllUsers() {
// // return Store.users;
// // }

// // @Override
// // public User getUserByUsername(String username) {

// // User userFromDb = null;

// // for (User user : Store.users) {
// // if (user == null) {
// // break;
// // }
// // if (user.getUsername().equals(username)) {
// // userFromDb = user;
// // break;
// // }
// // }
// // return userFromDb;
// // }

// // @Override
// // public void createUser(User user) {
// // Store.users.add(0, user);
// // // for (int i = 0; i < Store.users.length; i++) {
// // // if (Store.users[i] == null) {
// // // Store.users[i] = user;
// // // break;
// // // }
// // }
// }
