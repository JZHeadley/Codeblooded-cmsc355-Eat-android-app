package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Categories implements Parcelable {

    public static final Parcelable.Creator<Categories> CREATOR = new Parcelable
        .Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel source) {
            return new Categories(source);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;

    public Categories() {
    }

    public Categories(String href) {
        this.href = href;
    }

    protected Categories(Parcel in) {
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
        return "Categories{"
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
