package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "Province")
public class Province extends Model {


    @Expose
    @SerializedName("id")
    @Column(name = "province_id")
    private String provinceId;

    @SerializedName("provinceName")
    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("parentId")
    @Column(name = "parentId")
    private String parentId;

    public Province() {
        super();
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
