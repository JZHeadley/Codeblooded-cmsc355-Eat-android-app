
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menus {

    @SerializedName("href")
    @Expose
    private String href;

    public Menus() {
    }

    public Menus(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "href='" + href + '\'' +
                '}';
    }
}
