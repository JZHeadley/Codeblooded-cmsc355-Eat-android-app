
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Menu() {
    }

    public Menu(String menuName, Links links) {
        this.menuName = menuName;
        this.links = links;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", links=" + links +
                '}';
    }
}
