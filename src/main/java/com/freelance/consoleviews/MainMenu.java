package com.freelance.consoleviews;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freelance.models.User;
import com.freelance.services.UserService;
import com.freelance.util.InputUtil;

public class MainMenu {

    static Logger logger = LogManager.getLogger(MainMenu.class);
    UserService userService = new UserService();
    InputUtil inputUtil = new InputUtil();

    public void view() {

        Scanner scanner = new Scanner(System.in);

        Boolean running = true;

        while (running) {
            System.out.println("Grocer List App!\n1) login\n2) register\n0) EXIT");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    String usernameInput = inputUtil.retrieveString("Username: ");
                    String passwordInput = inputUtil.retrieveString("Password: ");

                    Boolean areCredentialsValid = userService
                            .validateCredentials(new User(usernameInput, passwordInput));
                    if (areCredentialsValid) {
                        logger.info(usernameInput + " has successfully logged into the system");
                        User userFromDb = this.userService.getUserGivenUsername(usernameInput);
                        new Dashboard().view(userFromDb);
                    } else {
                        System.out.println("Invalid username or password");
                    }
                    break;

                case "2":
                    String usernameInputReg = inputUtil.retrieveString("Username: ");
                    String passwordInputReg = inputUtil.retrieveString("Password: ");
                    String firstNameInputReg = inputUtil.retrieveString("First Name: ");
                    String lastNameInputReg = inputUtil.retrieveString("Last Name: ");

                    // System.out.print("Password: ");
                    // String registerPasswordInput = scanner.nextLine();

                    // System.out.print("First Name: ");
                    // String registerFirstnameInput = scanner.nextLine();

                    // System.out.print("Last Name: ");
                    // String registerLastname = scanner.nextLine();

                    User userToCreate = new User(usernameInputReg, firstNameInputReg, lastNameInputReg,
                            passwordInputReg);

                    User userFromDb = this.userService.createUser(userToCreate);

                    if (userFromDb == null) {
                        System.out.println("Username already exists... aborting");
                    } else {
                        System.out.println("User Created");
                    }
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
        scanner.close();
    }
}
