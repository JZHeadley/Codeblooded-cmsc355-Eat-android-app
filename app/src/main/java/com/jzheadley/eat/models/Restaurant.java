package com.jzheadley.eat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant implements Parcelable {


    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pictureurl")
    @Expose
    private String pictureurl;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    private String description;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Restaurant() {
    }

    public Restaurant(String name, String pictureurl, String location, String description, Links links) {
        this.name = name;
        this.pictureurl = pictureurl;
        this.location = location;
        this.description = description;
        this.links = links;
    }

    protected Restaurant(Parcel in) {
        this.name = in.readString();
        this.pictureurl = in.readString();
        this.location = in.readString();
        this.description = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.pictureurl);
        dest.writeString(this.location);
        dest.writeString(this.description);
        dest.writeParcelable(this.links, flags);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", location='" + location + '\'' +
                ", links=" + links +
                '}';
    }

}
