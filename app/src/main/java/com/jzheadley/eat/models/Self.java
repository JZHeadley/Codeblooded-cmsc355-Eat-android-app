package com.jzheadley.eat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Self implements Parcelable {

    public static final Parcelable.Creator<Self> CREATOR = new Parcelable.Creator<Self>() {
        @Override
        public Self createFromParcel(Parcel source) {
            return new Self(source);
        }

        @Override
        public Self[] newArray(int size) {
            return new Self[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;

    public Self() {
    }

    public Self(String href) {
        this.href = href;
    }

    protected Self(Parcel in) {
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
        return "Self{"
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
