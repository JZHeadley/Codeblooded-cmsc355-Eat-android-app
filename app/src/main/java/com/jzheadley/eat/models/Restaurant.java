
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pictureurl")
    @Expose
    private String pictureurl;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Restaurant() {
    }

    public Restaurant(String name, String pictureurl, String location, Links links) {
        this.name = name;
        this.pictureurl = pictureurl;
        this.location = location;
        this.links = links;
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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", location='" + location + '\'' +
                ", links=" + links +
                '}';
    }
}
