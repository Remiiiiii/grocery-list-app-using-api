package com.freelance.controllers;

import java.util.ArrayList;
import java.util.List;

import com.freelance.models.GroceryItem;
import com.freelance.models.JsonResponse;
import com.freelance.models.User;
import com.freelance.services.GroceryItemService;

import io.javalin.http.Context;

public class GroceryItemController {

    GroceryItemService groceryItemService = new GroceryItemService();

    public void getAllItemsGivenUserId(Context ctx) {

        User user = ctx.sessionAttribute("user");

        if (user == null) {

            ctx.json(new JsonResponse(false, "", "items are unretrievable because no session was found"));

            return;
        }

        List<GroceryItem> item = groceryItemService.getAllGroceryItemsGivenUserId(user.getId());

        ctx.json(new JsonResponse(true, "retrieving all items for: " + user.getFirstname(), item));
    }

    public void createItem(Context ctx) {

        GroceryItem item = ctx.bodyAsClass(GroceryItem.class);

        User userFromSession = ctx.sessionAttribute("user");

        item.setUserIdFK(userFromSession.getId());

        groceryItemService.createGroceryItem(item);

        ctx.json(new JsonResponse(true, "Item created", null));

    }

    public void deleteItem(Context ctx) {

        Integer itemId = Integer.parseInt(ctx.pathParam("itemId"));

        groceryItemService.deleteAGroceryitemGivenGroceryId(itemId);

        ctx.json(new JsonResponse(true, "Item deleted if exists", null));

    }

    public void markItemComplete(Context ctx) {

        Integer item = Integer.parseInt(ctx.pathParam("itemId"));

        groceryItemService.markInCartivenGroceryId(item);

        ctx.json(new JsonResponse(true, "Item marked in cart if exists", null));

    }
}
