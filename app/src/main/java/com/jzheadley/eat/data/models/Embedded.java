package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Embedded implements Parcelable {


    public static final Creator<Embedded> CREATOR = new Creator<Embedded>() {
        @Override
        public Embedded createFromParcel(Parcel source) {
            return new Embedded(source);
        }

        @Override
        public Embedded[] newArray(int size) {
            return new Embedded[size];
        }
    };
    @SerializedName("menuItems")
    @Expose
    private List<MenuItem> menuItems = new ArrayList<>();
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<>();
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = new ArrayList<>();
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants = new ArrayList<>();
    @SerializedName("users")
    @Expose
    private List<User> users = new ArrayList<User>();

    public Embedded() {
    }

    public Embedded(List<?> items) {
        if (!(items.size() > 0)) {
            throw new IllegalArgumentException("no items in response");
        }
        if (items.get(1) instanceof MenuItem) {
            this.menuItems = (List<MenuItem>) items;
        } else if (items.get(1) instanceof Category) {
            this.categories = (List<Category>) items;
        } else if (items.get(1) instanceof Menu) {
            this.menus = (List<Menu>) items;
        } else if (items.get(1) instanceof Restaurant) {
            this.restaurants = (List<Restaurant>) items;
        } else if (items.get(1) instanceof User) {
            this.users = (List<User>) items;
        }
    }

    private Embedded(Parcel in) {
        this.menuItems = in.createTypedArrayList(MenuItem.CREATOR);
        this.categories = in.createTypedArrayList(Category.CREATOR);
        this.menus = in.createTypedArrayList(Menu.CREATOR);
        this.restaurants = in.createTypedArrayList(Restaurant.CREATOR);
        this.users = new ArrayList<>();
        in.readList(this.users, User.class.getClassLoader());
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Embedded{"
            + "menuItems=" + menuItems
            + ", categories=" + categories
            + ", menus=" + menus
            + ", restaurants=" + restaurants
            + '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.menuItems);
        dest.writeTypedList(this.categories);
        dest.writeTypedList(this.menus);
        dest.writeTypedList(this.restaurants);
        dest.writeList(this.users);
    }
}
