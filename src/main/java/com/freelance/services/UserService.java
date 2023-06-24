package com.freelance.services;

import com.freelance.dao.UserDao;
import com.freelance.dao.UserDaoImpl;
import com.freelance.models.User;
// import com.freelance.store.Store;

public class UserService {

    UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean validateCredentials(User credentials) {

        User userFromDb = userDao.getUserByUsername(credentials.getUsername());
        if (userFromDb == null) {
            return false;
        }

        if (userFromDb.getPassword().equals(credentials.getPassword())) {
            return true;
        }

        return false;
    }

    public User getUserGivenUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User createUser(User userToCreate) {
        User userFromDb = userDao.getUserByUsername(userToCreate.getUsername());

        if (userFromDb == null) {
            userDao.createUser(userToCreate);
            return userToCreate;
        }
        return null;
    }
}
