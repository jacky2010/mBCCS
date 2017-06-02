package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class StaffInfo implements Parcelable {

    @SerializedName("StaffId")
    @Expose
    private long staffId;

    @SerializedName("StaffCode")
    @Expose
    private String staffCode;

    @SerializedName("StaffName")
    @Expose
    private String staffName;

    @SerializedName("ShopId")
    @Expose
    private long shopId;

    @SerializedName("ShopCode")
    @Expose
    private String shopCode;

    @SerializedName("ShopName")
    @Expose
    private String shopName;

    @SerializedName("ChannelTypeId")
    @Expose
    private long channelTypeId;

    private String tel;

    private String staffJobTitle;

    private String staffAddress;

    public StaffInfo() {
    }

    public StaffInfo(long staffId, String staffCode, String staffName, long shopId, String shopCode,
                     String shopName, long channelTypeId, String tel, String staffJobTitle,
                     String staffAddress) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.shopId = shopId;
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.channelTypeId = channelTypeId;
        this.tel = tel;
        this.staffJobTitle = staffJobTitle;
        this.staffAddress = staffAddress;
    }

    public StaffInfo(Parcel in) {
        staffId = in.readLong();
        staffCode = in.readString();
        staffName = in.readString();
        shopId = in.readLong();
        shopCode = in.readString();
        shopName = in.readString();
        channelTypeId = in.readLong();
        tel = in.readString();
        staffJobTitle = in.readString();
        staffAddress = in.readString();
    }

    public static final Creator<StaffInfo> CREATOR = new Creator<StaffInfo>() {
        @Override
        public StaffInfo createFromParcel(Parcel in) {
            return new StaffInfo(in);
        }

        @Override
        public StaffInfo[] newArray(int size) {
            return new StaffInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(staffId);
        dest.writeString(staffCode);
        dest.writeString(staffName);
        dest.writeLong(shopId);
        dest.writeString(shopCode);
        dest.writeString(shopName);
        dest.writeLong(channelTypeId);
        dest.writeString(tel);
        dest.writeString(staffJobTitle);
        dest.writeString(staffAddress);
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStaffJobTitle() {
        return staffJobTitle;
    }

    public void setStaffJobTitle(String staffJobTitle) {
        this.staffJobTitle = staffJobTitle;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }
}
