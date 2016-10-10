package com.jzheadley.eat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants;

    public User(String name, Links links) {

        this.name = name;
        this.links = links;
    }

    public User(String name) {

        this.name = name;
    }

    public User(String name, Links links, String href) {
        this.name = name;
        this.links = links;
        this.href = href;
    }

    private User(Parcel in) {
        this.name = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
        this.href = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", links=" + links +
                ", href='" + href + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.links, flags);
        dest.writeString(this.href);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
