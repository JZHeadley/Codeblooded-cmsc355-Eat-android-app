package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurants {

    @SerializedName("href")
    @Expose
    private String href;

    public Restaurants() {
    }

    public Restaurants(String href) {

        this.href = href;
    }

    /**
     * @return The href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                "href='" + href + '\'' +
                '}';
    }
}
