package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class StaffInfo implements Parcelable {

    @SerializedName("staffId")
    @Expose
    private long staffId;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("name")
    @Expose
    private String staffName;

    @SerializedName("shopId")
    @Expose
    private long shopId;

    @SerializedName("channelTypeId")
    @Expose
    private long channelTypeId;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("idIssueDate")
    @Expose
    private String idIssueDate;

    @SerializedName("pricePolicy")
    @Expose
    private String pricePolicy;

    @SerializedName("idNo")
    @Expose
    private String idNo;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("staffOwnerId")
    @Expose
    private String staffOwnerId;

    @SerializedName("discountPolicy")
    @Expose
    private String discountPolicy;

    @SerializedName("tel")
    @Expose
    private String tel;

    @SerializedName("idIssuePlace")
    @Expose
    private String idIssuePlace;

    @SerializedName("status")
    @Expose
    private String status;

    private String staffJobTitle;

    private String staffAddress;

    public StaffInfo() {
    }

    public StaffInfo(long staffId, String staffCode, String staffName, long shopId,
            long channelTypeId, String birthday, String address, String idIssueDate,
            String pricePolicy, String idNo, String province, String staffOwnerId,
            String discountPolicy, String tel, String idIssuePlace, String status,
            String staffJobTitle, String staffAddress) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.shopId = shopId;
        this.channelTypeId = channelTypeId;
        this.birthday = birthday;
        this.address = address;
        this.idIssueDate = idIssueDate;
        this.pricePolicy = pricePolicy;
        this.idNo = idNo;
        this.province = province;
        this.staffOwnerId = staffOwnerId;
        this.discountPolicy = discountPolicy;
        this.tel = tel;
        this.idIssuePlace = idIssuePlace;
        this.status = status;
        this.staffJobTitle = staffJobTitle;
        this.staffAddress = staffAddress;
    }

    protected StaffInfo(Parcel in) {
        staffId = in.readLong();
        staffCode = in.readString();
        staffName = in.readString();
        shopId = in.readLong();
        channelTypeId = in.readLong();
        birthday = in.readString();
        address = in.readString();
        idIssueDate = in.readString();
        pricePolicy = in.readString();
        idNo = in.readString();
        province = in.readString();
        staffOwnerId = in.readString();
        discountPolicy = in.readString();
        tel = in.readString();
        idIssuePlace = in.readString();
        status = in.readString();
        staffJobTitle = in.readString();
        staffAddress = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(staffId);
        dest.writeString(staffCode);
        dest.writeString(staffName);
        dest.writeLong(shopId);
        dest.writeLong(channelTypeId);
        dest.writeString(birthday);
        dest.writeString(address);
        dest.writeString(idIssueDate);
        dest.writeString(pricePolicy);
        dest.writeString(idNo);
        dest.writeString(province);
        dest.writeString(staffOwnerId);
        dest.writeString(discountPolicy);
        dest.writeString(tel);
        dest.writeString(idIssuePlace);
        dest.writeString(status);
        dest.writeString(staffJobTitle);
        dest.writeString(staffAddress);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(String idIssueDate) {
        this.idIssueDate = idIssueDate;
    }

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStaffOwnerId() {
        return staffOwnerId;
    }

    public void setStaffOwnerId(String staffOwnerId) {
        this.staffOwnerId = staffOwnerId;
    }

    public String getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(String discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdIssuePlace() {
        return idIssuePlace;
    }

    public void setIdIssuePlace(String idIssuePlace) {
        this.idIssuePlace = idIssuePlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
