package com.jzheadley.eat.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItems implements Parcelable {
    public static final Parcelable.Creator<MenuItems> CREATOR = new Parcelable.Creator<MenuItems>() {
        @Override
        public MenuItems createFromParcel(Parcel source) {
            return new MenuItems(source);
        }

        @Override
        public MenuItems[] newArray(int size) {
            return new MenuItems[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;

    public MenuItems() {
    }

    public MenuItems(String href) {
        this.href = href;
    }

    protected MenuItems(Parcel in) {
        this.href = in.readString();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "MenuItems{"
                + "href='" + href + '\''
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
    }
}
