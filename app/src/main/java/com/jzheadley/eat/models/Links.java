package com.jzheadley.eat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Parcelable {

    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
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
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("restaurants")
    @Expose
    private Restaurants restaurants;

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

    private Links(Parcel in) {
        this.self = in.readParcelable(Self.class.getClassLoader());
        this.menuItem = in.readParcelable(MenuItem.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.menuItems = in.readParcelable(MenuItems.class.getClassLoader());
        this.menu = in.readParcelable(Menu.class.getClassLoader());
        this.categories = in.readParcelable(Categories.class.getClassLoader());
        this.restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        this.menus = in.readParcelable(Menus.class.getClassLoader());
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
        return "Links{"
                + "self=" + self
                + ", menuItem=" + menuItem
                + ", category=" + category
                + ", menuItems=" + menuItems
                + ", menu=" + menu
                + ", categories=" + categories
                + ", restaurant=" + restaurant
                + ", menus=" + menus
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.self, flags);
        dest.writeParcelable(this.menuItem, flags);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.menuItems, flags);
        dest.writeParcelable(this.menu, flags);
        dest.writeParcelable(this.categories, flags);
        dest.writeParcelable(this.restaurant, flags);
        dest.writeParcelable(this.menus, flags);
    }

    public Restaurants getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
