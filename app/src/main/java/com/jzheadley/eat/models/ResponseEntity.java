
package com.jzheadley.eat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseEntity {

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
        return "ResponseEntity{" +
                "embedded=" + embedded +
                ", links=" + links +
                '}';
    }
}
