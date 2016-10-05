package com.jzheadley.eat.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    private String categoryName;
    @SerializedName("_links")
    private Links links;
    private String href;

    public Category() {
    }

    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
