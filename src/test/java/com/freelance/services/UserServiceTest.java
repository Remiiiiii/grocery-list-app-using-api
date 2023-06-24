package com.freelance.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.freelance.dao.UserDao;
import com.freelance.models.User;

public class UserServiceTest {

    UserDao userDao = Mockito.mock(UserDao.class);

    UserService userService = new UserService(userDao);

    @Test
    void testCreateUser() {

    }

    @Test
    void testGetUserGivenUsername() {

    }

    @Test
    void testValidateCredentials() {
        // arrange
        User objToPass = new User("remy123", "pass123");
        Mockito.when(userDao.getUserByUsername(objToPass.getUsername())).thenReturn(objToPass);

        // act (calling the method)
        Boolean actualResult = userService.validateCredentials(objToPass);

        // assert
        Assertions.assertTrue(actualResult);
    }

    @Test
    void testValidateCredentialsInvalidUsername() {
        // arrange
        User objToPass = new User("remy123", "pass123");
        Mockito.when(userDao.getUserByUsername(objToPass.getUsername())).thenReturn(null);

        // act (calling the method)
        Boolean actualResult = userService.validateCredentials(objToPass);

        // assert
        Assertions.assertFalse(actualResult);
    }

    @Test
    void testValidateCredentialsInvalidPassword() {
        // arrange
        User objToPass = new User("remy123", "pass123");
        User objReturnedFromDb = new User("remy123", "pass1234");
        Mockito.when(userDao.getUserByUsername(objToPass.getUsername())).thenReturn(objReturnedFromDb);

        // act (calling the method)
        Boolean actualResult = userService.validateCredentials(objToPass);

        // assert
        Assertions.assertFalse(actualResult);
    }
}
