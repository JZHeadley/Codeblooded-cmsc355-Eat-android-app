
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItem {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("_links")
    @Expose
    private Links links;

    public MenuItem() {
    }

    public MenuItem(String name, String price, String description, Links links) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.links = links;
    }

    public MenuItem(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public MenuItem(String name, String description) {
        this.name = name;
        this.price = "Please ask your waiter for the current pricing";
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", links=" + links +
                '}';
    }
}
