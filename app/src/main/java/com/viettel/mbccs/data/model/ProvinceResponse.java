package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class ProvinceResponse {

    @SerializedName("id")
    @Expose
    private String provinceId;

    @SerializedName("provinceName")
    @Expose
    private String name;

    @SerializedName("parentId")
    @Expose
    private String parentId;

    public ProvinceResponse() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
