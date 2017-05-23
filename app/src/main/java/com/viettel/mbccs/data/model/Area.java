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
    @SerializedName("AreaCode")
    private String areaCode;

    @Expose
    @SerializedName("ProvinceCode")
    private String provinceCode;

    @Expose
    @SerializedName("District")
    private String district;

    @Expose
    @SerializedName("Precinct")
    private String precinct;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("FullName")
    private String fullName;

    public Area() {
    }

    protected Area(Parcel in) {
        areaCode = in.readString();
        provinceCode = in.readString();
        district = in.readString();
        precinct = in.readString();
        name = in.readString();
        fullName = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(areaCode);
        dest.writeString(provinceCode);
        dest.writeString(district);
        dest.writeString(precinct);
        dest.writeString(name);
        dest.writeString(fullName);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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
}
