package com.freelance.services;

import java.util.List;

import com.freelance.dao.GroceryItemDao;
import com.freelance.dao.GroceryItemDaoImpl;
import com.freelance.models.GroceryItem;

public class GroceryItemService {

    GroceryItemDao groceryItemDao = new GroceryItemDaoImpl();

    public void createGroceryItem(GroceryItem groceryItem) {
        this.groceryItemDao.createGroceryItem(groceryItem);
    }

    public List<GroceryItem> getAllGroceryItemsGivenUserId(Integer userId) {
        return this.groceryItemDao.getAllItemsGivenUserId(userId);
    }

    public void deleteAGroceryitemGivenGroceryId(Integer groceryItemId) {
        this.groceryItemDao.deleteGroceryItem(groceryItemId);
    }

    public void markInCartivenGroceryId(Integer groceryItemId) {
        this.groceryItemDao.markGroceryItemInCart(groceryItemId);

    }
}
