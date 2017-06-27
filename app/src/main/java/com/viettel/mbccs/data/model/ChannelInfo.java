package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class ChannelInfo implements Parcelable, Serializable {

    public static final String CHANNEL_TYPE_SHOP = "1";
    public static final String CHANNEL_TYPE_STAFF = "2";

    @SerializedName("channelId")
    @Expose
    private long channelId;

    @SerializedName("channelCode")
    @Expose
    private String channelCode;

    @SerializedName("channelType")
    @Expose
    private long channelType;

    @SerializedName("channelName")
    @Expose
    private String channelName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("tel")
    @Expose
    private String tel;

    @SerializedName("managementId")
    @Expose
    private long managementId;

    @SerializedName("managementCode")
    @Expose
    private String managementCode;

    @SerializedName("managementType")
    @Expose
    private String managementType;

    @SerializedName("managementName")
    @Expose
    private String managementName;

    @SerializedName("shopId")
    @Expose
    private long shopId;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("shopName")
    @Expose
    private String shopName;

    @SerializedName("pricePolicy")
    @Expose
    private String pricePolicy;

    @SerializedName("discountPolicy")
    @Expose
    private String discountPolicy;

    public ChannelInfo() {
    }

    public ChannelInfo(long channelId, String channelCode, long channelType, String channelName,
            String address, String tel, long managementId, String managementCode,
            String managementType, String managementName, long shopId, String shopCode,
            String shopName, String pricePolicy, String discountPolicy) {
        this.channelId = channelId;
        this.channelCode = channelCode;
        this.channelType = channelType;
        this.channelName = channelName;
        this.address = address;
        this.tel = tel;
        this.managementId = managementId;
        this.managementCode = managementCode;
        this.managementType = managementType;
        this.managementName = managementName;
        this.shopId = shopId;
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.pricePolicy = pricePolicy;
        this.discountPolicy = discountPolicy;
    }

    public ChannelInfo(long channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    protected ChannelInfo(Parcel in) {
        channelId = in.readLong();
        channelCode = in.readString();
        channelType = in.readLong();
        channelName = in.readString();
        address = in.readString();
        tel = in.readString();
        managementId = in.readLong();
        managementCode = in.readString();
        managementType = in.readString();
        managementName = in.readString();
        shopId = in.readLong();
        shopCode = in.readString();
        shopName = in.readString();
        pricePolicy = in.readString();
        discountPolicy = in.readString();
    }

    public static final Creator<ChannelInfo> CREATOR = new Creator<ChannelInfo>() {
        @Override
        public ChannelInfo createFromParcel(Parcel in) {
            return new ChannelInfo(in);
        }

        @Override
        public ChannelInfo[] newArray(int size) {
            return new ChannelInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(channelId);
        dest.writeString(channelCode);
        dest.writeLong(channelType);
        dest.writeString(channelName);
        dest.writeString(address);
        dest.writeString(tel);
        dest.writeLong(managementId);
        dest.writeString(managementCode);
        dest.writeString(managementType);
        dest.writeString(managementName);
        dest.writeLong(shopId);
        dest.writeString(shopCode);
        dest.writeString(shopName);
        dest.writeString(pricePolicy);
        dest.writeString(discountPolicy);
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public long getChannelType() {
        return channelType;
    }

    public void setChannelType(long channelType) {
        this.channelType = channelType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getManagementId() {
        return managementId;
    }

    public void setManagementId(long managementId) {
        this.managementId = managementId;
    }

    public String getManagementCode() {
        return managementCode;
    }

    public void setManagementCode(String managementCode) {
        this.managementCode = managementCode;
    }

    public String getManagementType() {
        return managementType;
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }

    public String getManagementName() {
        return managementName;
    }

    public void setManagementName(String managementName) {
        this.managementName = managementName;
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

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public String getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(String discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public String toString() {
        return getManagementName();
    }
}
