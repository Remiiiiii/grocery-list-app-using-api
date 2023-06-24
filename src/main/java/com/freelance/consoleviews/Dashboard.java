package com.freelance.consoleviews;

import java.util.List;

import com.freelance.models.GroceryItem;
import com.freelance.models.User;
import com.freelance.services.GroceryItemService;
import com.freelance.util.InputUtil;

public class Dashboard {

    GroceryItemService groceryItemService = new GroceryItemService();
    InputUtil inputUtil = new InputUtil();

    public void view(User userFromDb) {
        Boolean running = true;
        while (running) {
            System.out.println("Welcome " + userFromDb.getFirstname() + "!");
            printItems(userFromDb);
            System.out.println("\n1) Create item\n2) Mark item 'in cart'\n3) delete item \n0) logout");
            String input = inputUtil.retrieveString("\nChoose: ");

            switch (input) {
                case "1":
                    String groceryname = inputUtil.retrieveString("Name of Item: ");
                    Integer groceryQty = inputUtil.retrieveInt("Amount: ");
                    this.groceryItemService
                            .createGroceryItem(new GroceryItem(groceryname, groceryQty, userFromDb.getId()));
                    System.out.println("Item Created!");
                    break;
                case "2":
                    Integer itemId = inputUtil.retrieveInt("Item Id: ");
                    this.groceryItemService.markInCartivenGroceryId(itemId);
                    System.out.println("Marked in cart if item Id exists");
                    break;
                case "3":
                    Integer groceryItemId = inputUtil.retrieveInt("Item Id: ");
                    this.groceryItemService.deleteAGroceryitemGivenGroceryId(groceryItemId);
                    System.out.println("item deleted if item Id exists");
                    break;
                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private void printItems(User userFromDb) {
        List<GroceryItem> items = this.groceryItemService.getAllGroceryItemsGivenUserId(userFromDb.getId());

        for (GroceryItem groceryItems : items) {
            System.out.println(groceryItems);
        }
    }

}
