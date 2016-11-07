package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurants implements Parcelable {

    public static final Creator<Restaurants> CREATOR = new Creator<Restaurants>() {
        @Override
        public Restaurants createFromParcel(Parcel source) {
            return new Restaurants(source);
        }

        @Override
        public Restaurants[] newArray(int size) {
            return new Restaurants[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;

    public Restaurants() {
    }

    public Restaurants(String href) {

        this.href = href;
    }

    protected Restaurants(Parcel in) {
        this.href = in.readString();
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
        return "Restaurants{"
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

