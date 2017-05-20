package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TeleComService implements Serializable{

    @SerializedName("TelecomServiceId")
    @Expose
    private long id;

    @SerializedName("TelecomServiceName")
    @Expose
    private String name;

    public TeleComService() {
    }

    public TeleComService(long id, String name) {
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

