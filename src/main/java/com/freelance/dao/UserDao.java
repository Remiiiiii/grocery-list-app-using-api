package com.freelance.dao;

import java.util.List;

import com.freelance.models.User;
// import com.freelance.store.Store;

/* An interface is a contract between the interface itself,
 * and the classes that implement it
 */

public interface UserDao {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    void createUser(User user);

}
