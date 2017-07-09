package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 7/6/17.
 */

public class OwnerCode implements Parcelable {

    @SerializedName("shopCode")
    @Expose
    private String shopCode;
    @SerializedName("staffCode")
    @Expose
    private String staffCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("channelTypeId")
    @Expose
    private long channelTypeId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopId")
    @Expose
    private long shopId;
    @SerializedName("staffId")
    @Expose
    private long staffId;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shopCode);
        dest.writeString(this.staffCode);
        dest.writeString(this.name);
        dest.writeLong(this.channelTypeId);
        dest.writeString(this.shopName);
        dest.writeLong(this.shopId);
        dest.writeLong(this.staffId);
    }

    public OwnerCode() {
    }

    protected OwnerCode(Parcel in) {
        this.shopCode = in.readString();
        this.staffCode = in.readString();
        this.name = in.readString();
        this.channelTypeId = in.readLong();
        this.shopName = in.readString();
        this.shopId = in.readLong();
        this.staffId = in.readLong();
    }

    public static final Parcelable.Creator<OwnerCode> CREATOR =
            new Parcelable.Creator<OwnerCode>() {
                @Override
                public OwnerCode createFromParcel(Parcel source) {
                    return new OwnerCode(source);
                }

                @Override
                public OwnerCode[] newArray(int size) {
                    return new OwnerCode[size];
                }
            };
}
