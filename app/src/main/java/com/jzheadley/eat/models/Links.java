
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("menuItem")
    @Expose
    private MenuItem menuItem;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("menuItems")
    @Expose
    private MenuItems menuItems;
    @SerializedName("menu")
    @Expose
    private Menu menu;
    @SerializedName("categories")
    @Expose
    private Categories categories;
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;
    @SerializedName("menus")
    @Expose
    private Menus menus;

    public Links() {
    }

    public Links(Self self, MenuItem menuItem) {
        this.self = self;
        this.menuItem = menuItem;
    }

    public Links(Self self, Category category, MenuItems menuItems) {
        this.self = self;
        this.category = category;
        this.menuItems = menuItems;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItems menuItems) {
        this.menuItems = menuItems;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Links{" +
                "self=" + self +
                ", menuItem=" + menuItem +
                ", category=" + category +
                ", menuItems=" + menuItems +
                ", menu=" + menu +
                ", categories=" + categories +
                ", restaurant=" + restaurant +
                ", menus=" + menus +
                '}';
    }
}
