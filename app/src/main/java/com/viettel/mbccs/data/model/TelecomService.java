package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TelecomService implements Serializable{

    @SerializedName("telecomServiceId")
    @Expose
    private long id;

    @SerializedName("telServiceName")
    @Expose
    private String name;

    public TelecomService() {
    }

    public TelecomService(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}

