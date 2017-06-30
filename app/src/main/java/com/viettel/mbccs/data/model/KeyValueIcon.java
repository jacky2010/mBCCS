package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by minhnx on 5/21/17.
 */

public class KeyValueIcon implements Serializable {
    @Expose
    @SerializedName("resourceId")
    private int resourceId;
    @Expose
    @SerializedName("key")
    private String key;
    @Expose
    @SerializedName("value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public KeyValueIcon(int resourceId, String key, String value) {
        this.resourceId = resourceId;
        this.key = key;
        this.value = value;
    }

    public KeyValueIcon() {
    }
}
