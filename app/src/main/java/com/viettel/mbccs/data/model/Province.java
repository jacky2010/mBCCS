package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "Province")
public class Province extends Model {

    @Column(name = "province_id")
    private long provinceId;

    @Column(name = "name")
    private String name;

    public Province() {
        super();
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