package com.jzheadley.eat.models;

import com.google.gson.annotations.SerializedName;


public class Restaurant {
    private String name;
    private String pictureurl;
    private String location;
    private String href;
    @SerializedName("_links")
    private Links links;

    public Restaurant() {
    }

    public Restaurant(String name, String pictureurl, String location) {
        this.name = name;
        this.pictureurl = pictureurl;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
