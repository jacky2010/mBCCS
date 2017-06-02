package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/30/17.
 */

public class PrecinctResponse {

    @SerializedName("id")
    @Expose
    private String precinctId;

    @SerializedName("precinctName")
    @Expose
    private String name;

    @SerializedName("parentId")
    @Expose
    private String districtId;

    private String provinceId;

    public PrecinctResponse() {

    }

    public PrecinctResponse(String precinctId, String name, String districtId, String provinceId) {
        this.precinctId = precinctId;
        this.name = name;
        this.districtId = districtId;
        this.provinceId = provinceId;
    }

    public String getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(String precinctId) {
        this.precinctId = precinctId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
