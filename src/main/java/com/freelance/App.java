package com.freelance;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freelance.dao.UserDao;
import com.freelance.dao.UserDaoImpl;
import com.freelance.consoleviews.MainMenu;
import com.freelance.util.InputUtil;
import com.freelance.models.User;

/**
 * Grocery List Specs
 * 
 * register a new user
 * login a new user
 * 
 * when logged in:
 * create a new grocery list item
 * define qty and value to see if item is in cart
 * 
 * delete a list item
 * update if the item is in the cart
 * persist data
 *
 * what does user name need?
 * -username(unique)
 * firstname
 * password
 */
public class App {

    static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        logger.debug("This is a debug log");

        MainMenu mainMenu = new MainMenu();
        mainMenu.view();

        // UserDao userDao = new UserDaoImpl();

        // User newUser = new User("timmy1234", "timmy123", "Timothy", "Turner");

        // userDao.createUser(newUser);

        // System.out.println(newUser);
        // List<User> userList = userDao.getAllUsers();

        // for (User user : userList) {
        // System.out.println(user);
        // }
        // System.out.println(userDao.getUserByUsername("remy123"));

        /*
         * User user = new User("remy", "victor", "ramirez", "pass123", 40);
         * System.out.println(user);
         */

        // InputUtil input = new InputUtil();

        // int intFromUser = input.retrieveInt("Give me a number: ");
        // System.out.print("You inputed the number: " + intFromUser);

        // System.out.println(convertToPigLatin("Revature"));

    }

    // public static String convertToPigLatin(String word) {
    // StringBuilder temp = new StringBuilder(word);
    // temp.deleteCharAt(0).append(temp.charAt(0)).append("ay");
    // return temp.toString();
    // }
}
