package com.jzheadley.eat.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItem implements Parcelable {

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel source) {
            return new MenuItem(source);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("_links")
    @Expose
    private Links links;

    public MenuItem() {
    }

    public MenuItem(String name, String price, String description, Links links) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.links = links;
    }

    public MenuItem(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public MenuItem(String name, String description) {
        this.name = name;
        this.price = "Please ask your waiter for the current pricing";
        this.description = description;
    }

    protected MenuItem(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
        this.description = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "MenuItem{"
                + "name='" + name + '\''
                + ", price='" + price + '\''
                + ", description='" + description + '\''
                + ", links=" + links
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.description);
        dest.writeParcelable(this.links, flags);
    }
}
