package com.freelance.models;

public class GroceryItem {
    private Integer id;
    private String name;
    private Integer qty;
    private Boolean inCart;
    private Integer userIdFK;

    public GroceryItem() {
    }

    public GroceryItem(String name, Integer qty) {
        this.name = name;
        this.qty = qty;
    }

    public GroceryItem(String name, Integer qty, Integer userIdFK) {
        this.name = name;
        this.qty = qty;
        this.userIdFK = userIdFK;
    }

    public GroceryItem(Integer id, String name, Integer qty, Boolean inCart, Integer userIdFK) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.inCart = inCart;
        this.userIdFK = userIdFK;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean isInCart() {
        return this.inCart;
    }

    public Boolean getInCart() {
        return this.inCart;
    }

    public void setInCart(Boolean inCart) {
        this.inCart = inCart;
    }

    public Integer getUserIdFK() {
        return this.userIdFK;
    }

    public void setUserIdFK(Integer userIdFK) {
        this.userIdFK = userIdFK;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", qty='" + getQty() + "'" +
                ", inCart='" + isInCart() + "'" +
                ", userIdFK='" + getUserIdFK() + "'" +
                "}";
    }

}
