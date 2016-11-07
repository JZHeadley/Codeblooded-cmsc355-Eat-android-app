package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Menus implements Parcelable {

    public static final Parcelable.Creator<Menus> CREATOR = new Parcelable.Creator<Menus>() {
        @Override
        public Menus createFromParcel(Parcel source) {
            return new Menus(source);
        }

        @Override
        public Menus[] newArray(int size) {
            return new Menus[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;

    public Menus() {
    }

    public Menus(String href) {
        this.href = href;
    }

    protected Menus(Parcel in) {
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
        return "Menus{"
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
