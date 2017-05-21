package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class ChannelInfo implements Parcelable, Serializable {

    public static final String CHANNEL_TYPE_SHOP = "1";
    public static final String CHANNEL_TYPE_STAFF = "2";

    @SerializedName("ChannelId")
    @Expose
    private long channelId;

    @SerializedName("ChannelCode")
    @Expose
    private String channelCode;

    @SerializedName("ChannelType")
    @Expose
    private String channelType;

    @SerializedName("ChannelName")
    @Expose
    private String channelName;

    @SerializedName("Address")
    @Expose
    private String address;

    @SerializedName("Tel")
    @Expose
    private String tel;

    @SerializedName("ManagementId")
    @Expose
    private long managementId;

    @SerializedName("ManagementCode")
    @Expose
    private String managementCode;

    @SerializedName("ManagementType")
    @Expose
    private String managementType;

    @SerializedName("ManagementName")
    @Expose
    private String managementName;

    @SerializedName("ShopId")
    @Expose
    private long shopId;

    @SerializedName("ShopCode")
    @Expose
    private String shopCode;

    @SerializedName("ShopName")
    @Expose
    private String shopName;

    @SerializedName("PricePolicy")
    @Expose
    private String pricePolicy;

    @SerializedName("DiscountPolicy")
    @Expose
    private String discountPolicy;

    public ChannelInfo() {
    }

    public ChannelInfo(long channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    protected ChannelInfo(Parcel in) {
        channelId = in.readLong();
        channelCode = in.readString();
        channelType = in.readString();
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
        dest.writeString(channelType);
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

    public String getChannelType() {
        if (TextUtils.isEmpty(channelType)) {
            return "0";
        }
        return channelType;
    }

    public void setChannelType(String channelType) {
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
}
