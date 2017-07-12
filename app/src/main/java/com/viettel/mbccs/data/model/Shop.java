package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class Shop implements Parcelable {

    @SerializedName("shopId")
    @Expose
    private Long shopId;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("shopPath")
    @Expose
    private String shopPath;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("discountPolicy")
    @Expose
    private String discountPolicy;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("name")
    @Expose
    private String shopName;

    @SerializedName("channelTypeId")
    @Expose
    private long channelTypeId;

    @SerializedName("pricePolicy")
    @Expose
    private long pricePolicy;

    @SerializedName("createDate")
    @Expose
    private String createDate;

    @SerializedName("status")
    @Expose
    private String status;

    public Shop() {

    }

    public Shop(Long shopId, String shopCode, String shopPath, String address,
            String discountPolicy, String province, String shopName, long channelTypeId,
            long pricePolicy, String createDate, String status) {
        this.shopId = shopId;
        this.shopCode = shopCode;
        this.shopPath = shopPath;
        this.address = address;
        this.discountPolicy = discountPolicy;
        this.province = province;
        this.shopName = shopName;
        this.channelTypeId = channelTypeId;
        this.pricePolicy = pricePolicy;
        this.createDate = createDate;
        this.status = status;
    }

    protected Shop(Parcel in) {
        shopId = in.readLong();
        shopCode = in.readString();
        shopPath = in.readString();
        address = in.readString();
        discountPolicy = in.readString();
        province = in.readString();
        shopName = in.readString();
        channelTypeId = in.readLong();
        pricePolicy = in.readLong();
        createDate = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(shopId);
        dest.writeString(shopCode);
        dest.writeString(shopPath);
        dest.writeString(address);
        dest.writeString(discountPolicy);
        dest.writeString(province);
        dest.writeString(shopName);
        dest.writeLong(channelTypeId);
        dest.writeLong(pricePolicy);
        dest.writeString(createDate);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopPath() {
        return shopPath;
    }

    public void setShopPath(String shopPath) {
        this.shopPath = shopPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(String discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public long getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(long pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return shopName;
    }
}
