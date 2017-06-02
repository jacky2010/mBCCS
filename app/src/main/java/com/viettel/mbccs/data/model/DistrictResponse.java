package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/30/17.
 */

public class DistrictResponse {

    @SerializedName("id")
    @Expose
    private String districtId;

    @SerializedName("districtName")
    @Expose
    private String name;

    @SerializedName("parentId")
    @Expose
    private String provinceId;

    public DistrictResponse() {

    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
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
}
