package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class Area implements Parcelable {
    @Expose
    @SerializedName("area_code")
    private String areaCode;

    @Expose
    @SerializedName("parent_code")
    private String parentCode;

    @Expose
    @SerializedName("cen_code")
    private String cenCode;

    @Expose
    @SerializedName("province")
    private String province;

    @Expose
    @SerializedName("district")
    private String district;

    @Expose
    @SerializedName("precinct")
    private String precinct;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    @SerializedName("order_no")
    private String orderNo;

    public Area() {
    }

    protected Area(Parcel in) {
        areaCode = in.readString();
        parentCode = in.readString();
        cenCode = in.readString();
        province = in.readString();
        district = in.readString();
        precinct = in.readString();
        name = in.readString();
        fullName = in.readString();
        orderNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(areaCode);
        dest.writeString(parentCode);
        dest.writeString(cenCode);
        dest.writeString(province);
        dest.writeString(district);
        dest.writeString(precinct);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(orderNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

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

    @Override
    public String toString() {
        return getName();
    }
}
