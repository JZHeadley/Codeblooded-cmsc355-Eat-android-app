package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    public static final Parcelable.Creator<Menu> CREATOR = new Parcelable.Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel source) {
            return new Menu(source);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Menu() {
    }

    public Menu(String menuName, Links links) {
        this.menuName = menuName;
        this.links = links;
    }

    protected Menu(Parcel in) {
        this.menuName = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Menu menu = (Menu) obj;

        return getMenuName().equals(menu.getMenuName());

    }

    @Override
    public int hashCode() {
        return getMenuName().hashCode();
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

    @Override
    public String toString() {
        return "Menu{"
            + "menuName='" + menuName + '\''
            + ", links=" + links
            + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.menuName);
        dest.writeParcelable(this.links, flags);
    }

}

