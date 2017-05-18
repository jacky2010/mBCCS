package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class BaseItem {
    @Expose
    private int id;
    @Expose
    private String name;

    public BaseItem() {
    }

    public BaseItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
