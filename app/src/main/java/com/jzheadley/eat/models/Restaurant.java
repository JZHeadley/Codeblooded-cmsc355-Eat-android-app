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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("pictureurl")
    @Expose
    private String pictureurl;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("restaurantOwner")
    @Expose
    private User user;

    public Restaurant() {
    }

    public Restaurant(String name, String pictureurl, String location, String description,
                      String zipcode, String address, String city, String country, Links links, User user) {
        this.name = name;
        this.pictureurl = pictureurl;
        this.description = description;
        this.zipcode = zipcode;
        this.address = address;
        this.city = city;
        this.country = country;
        this.links = links;
        this.user = user;
    }

    protected Restaurant(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.zipcode = in.readString();
        this.address = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.pictureurl = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Restaurant{"
                + "name='" + name + '\''
                + ", pictureurl='" + pictureurl + '\''
                + ", links=" + links
                + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.zipcode);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.pictureurl);
        dest.writeParcelable(this.links, flags);
        dest.writeParcelable(this.user, flags);
    }
}

