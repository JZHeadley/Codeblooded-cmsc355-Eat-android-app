package com.jzheadley.eat.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

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
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("firebaseId")
    @Expose
    private String firebaseId;
    @SerializedName("_links")
    @Expose
    private Links links;

    public User() {
    }

    public User(String href, String username, String firebaseId, Links links) {
        this.href = href;
        this.username = username;
        this.firebaseId = firebaseId;
        this.links = links;
    }

    public User(String href, String username, Links links) {
        this.href = href;
        this.username = username;
        this.links = links;
    }

    protected User(Parcel in) {
        this.href = in.readString();
        this.username = in.readString();
        this.firebaseId = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "User{"
            + "name='" + username + '\''
            + ", firebaseId='" + firebaseId + '\''
            + ", links=" + links
            + '}';
    }

    /**
     * @return The name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The name
     */
    public void setUsername(String username) {
        this.username = username;
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
        dest.writeString(this.href);
        dest.writeString(this.username);
        dest.writeString(this.firebaseId);
        dest.writeParcelable(this.links, flags);
    }
}
