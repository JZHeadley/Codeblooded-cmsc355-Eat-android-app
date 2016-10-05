package com.jzheadley.eat.models;

import com.google.gson.annotations.SerializedName;

public class Menu {
    private String menuName;
    @SerializedName("_links")
    private Links links;
    private String href;

    public Menu() {

    }

    public Menu(String menuName) {

        this.menuName = menuName;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
