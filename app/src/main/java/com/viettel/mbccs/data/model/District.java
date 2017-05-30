package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "District")
public class District extends Model {

    @Column(name = "district_id")
    private long districtId;

    @Column(name = "name")
    private String name;

    @Column(name = "province_id")
    private long provinceId;

    public District() {
        super();
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }
}
