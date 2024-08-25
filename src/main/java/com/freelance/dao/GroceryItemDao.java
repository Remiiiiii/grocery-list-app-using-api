package com.freelance.dao;

import java.util.List;

import com.freelance.models.GroceryItem;

/* create new grocery item
 * get all grocery items given user id
 * delete grocery item
 * mark grocery item in cart
 */

public interface GroceryItemDao {

    void markGroceryItemInCart(Integer groceryItemId);

    List<GroceryItem> getAllItemsGivenUserId(Integer userId);

    void createGroceryItem(GroceryItem groceryItem);

    void deleteGroceryItem(Integer groceryItemId);

}

// private Integer id;
// private String name;
// private Integer qty;
// private Boolean inCart;
// private Integer userIdFK;