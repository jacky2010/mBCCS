package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "Precinct")
public class Precinct extends Model {

    @SerializedName("id")
    @Expose
    @Column(name = "precint_id", unique = true, notNull = false)
    private String precinctId;

    @SerializedName("precinctName")
    @Expose
    @Column(name = "name", notNull = false)
    private String name;

    @SerializedName("parentId")
    @Expose
    @Column(name = "district_id", notNull = false)
    private String districtId;

    @Column(name = "province_id", notNull = false)
    private String provinceId;

    public Precinct() {
        super();
    }

    public Precinct(String precinctId, String name, String districtId, String provinceId) {
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
