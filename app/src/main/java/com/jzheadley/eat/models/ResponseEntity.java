package com.jzheadley.eat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseEntity implements Parcelable {

    public static final Parcelable.Creator<ResponseEntity> CREATOR = new Parcelable.Creator<ResponseEntity>() {
        @Override
        public ResponseEntity createFromParcel(Parcel source) {
            return new ResponseEntity(source);
        }

        @Override
        public ResponseEntity[] newArray(int size) {
            return new ResponseEntity[size];
        }
    };
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("_links")
    @Expose
    private Links links;

    public ResponseEntity() {
    }

    public ResponseEntity(Embedded embedded, Links links) {
        this.embedded = embedded;
        this.links = links;
    }

    protected ResponseEntity(Parcel in) {
        this.embedded = in.readParcelable(Embedded.class.getClassLoader());
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "ResponseEntity{"
                + "embedded=" + embedded
                + ", links=" + links
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.embedded, flags);
        dest.writeParcelable(this.links, flags);
    }
}

