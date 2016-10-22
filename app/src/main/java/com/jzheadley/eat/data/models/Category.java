package com.jzheadley.eat.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
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

    protected Category(Parcel in) {
        this.categoryName = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
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
        return "Category{"
                + "categoryName='" + categoryName + '\''
                + ", links=" + links
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.categoryName);
        dest.writeParcelable(this.links, flags);
    }
}
