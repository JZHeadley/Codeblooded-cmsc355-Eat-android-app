package com.jzheadley.eat.models;

import com.google.gson.annotations.SerializedName;

public class MenuItem {
    private String name;
    private String price;
    private String description;
    private String href;
    @SerializedName("_links")
    private Links links;

    public MenuItem() {

    }

    public MenuItem(String name, String price, String description) {

        this.name = name;
        this.price = price;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
