package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
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
    @SerializedName("firebaseId")
    @Expose
    private String firebaseId;
    @SerializedName("_links")
    @Expose
    private Links links;

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.firebaseId = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The firebaseId
     */
    public String getFirebaseId() {
        return firebaseId;
    }

    /**
     * @param firebaseId The firebaseId
     */
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    /**
     * @return The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * @param links The _links
     */
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
        dest.writeString(this.firebaseId);
        dest.writeParcelable(this.links, flags);
    }
}
