package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "Precinct")
public class Precinct extends Model {

    @Column(name = "precint_id", unique = true, notNull = false)
    private long precintId;

    @Column(name = "name", notNull = false)
    private String name;

    @Column(name = "district_id", notNull = false)
    private long districtId;

    @Column(name = "province_id", notNull = false)
    private long provinceId;

    public Precinct() {
        super();
    }

    public Precinct(long precintId, String name, long districtId, long provinceId) {
        this.precintId = precintId;
        this.name = name;
        this.districtId = districtId;
        this.provinceId = provinceId;
    }

    public long getPrecintId() {
        return precintId;
    }

    public void setPrecintId(long precintId) {
        this.precintId = precintId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }
}
