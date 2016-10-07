
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Category() {
    }

    public Category(String categoryName, Links links) {
        this.categoryName = categoryName;
        this.links = links;
    }

    public Category(String testCategory) {
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

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", links=" + links +
                '}';
    }
}
