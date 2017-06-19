package com.viettel.mbccs.data.model.database;

import android.support.annotation.StringDef;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by HuyQuyet on 5/22/17.
 */

@Table(name = "AreaDataBase")
public class AreaDataBase extends Model {
    @StringDef({
            Columns.AREA_CODE, Columns.PARENT_CODE, Columns.CEN_CODE, Columns.PROVINCE,
            Columns.DISTRICT, Columns.PRECINCT, Columns.NAME, Columns.FULL_NAME, Columns.ORDER_NO,
            Columns.STATUS
    })
    public @interface Columns {
        String AREA_CODE = "area_code";
        String PARENT_CODE = "parent_code";
        String CEN_CODE = "cen_code";
        String PROVINCE = "province";
        String DISTRICT = "district";
        String PRECINCT = "precinct";
        String NAME = "name";
        String FULL_NAME = "full_name";
        String ORDER_NO = "order_no";
        String STATUS = "status";
    }

    @Column(name = Columns.AREA_CODE)
    private String areaCode;

    @Column(name = Columns.PARENT_CODE)
    private String parentCode;

    @Column(name = Columns.CEN_CODE)
    private String cenCode;

    @Column(name = Columns.PROVINCE)
    private String province;

    @Column(name = Columns.DISTRICT)
    private String district;

    @Column(name = Columns.PRECINCT)
    private String precinct;

    @Column(name = Columns.NAME)
    private String name;

    @Column(name = Columns.FULL_NAME)
    private String fullName;

    @Column(name = Columns.ORDER_NO)
    private String orderNo;

    @Column(name = Columns.STATUS)
    private boolean status;

    public AreaDataBase() {
        super();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCenCode() {
        return cenCode;
    }

    public void setCenCode(String cenCode) {
        this.cenCode = cenCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPrecinct() {
        return precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
