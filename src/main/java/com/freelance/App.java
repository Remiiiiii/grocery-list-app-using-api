package com.freelance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.controllers.GroceryItemController;
import com.freelance.controllers.SessionController;
import com.freelance.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Grocery List Specs
 * 
 * register a new user / register page
 * login a new user / login page
 * 
 * when logged in: / dashboard page
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

        UserController userController = new UserController();
        GroceryItemController groceryItemController = new GroceryItemController();
        SessionController sc = new SessionController();

        // MainMenu mainMenu = new MainMenu();
        // mainMenu.view();

        /* addStaticFiles is how to host the frontend */
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/frontend", Location.CLASSPATH);
        }).start(9000);

        app.post("/api/user", userController::register);

        app.get("/api/session", sc::checkSession);

        app.post("/api/session", sc::login);

        app.delete("/api/session", sc::logout);

        app.get("/api/item", groceryItemController::getAllItemsGivenUserId);
        app.post("api/item", groceryItemController::createItem);
        app.patch("api/item/{itemId}", groceryItemController::markItemInCart);
        app.delete("api/item/{itemId}", groceryItemController::deleteItem);

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
