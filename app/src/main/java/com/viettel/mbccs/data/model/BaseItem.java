package com.viettel.mbccs.data.model;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class BaseItem {
    private int id;
    private String name;

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
