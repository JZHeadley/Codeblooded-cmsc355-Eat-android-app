package com.jzheadley.eat.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Embedded {
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private List<Menu> menus = new ArrayList<Menu>();
    private List<Category> categories = new ArrayList<Category>();
    @SerializedName("_links")
    private Links links;

    public Embedded() {
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
