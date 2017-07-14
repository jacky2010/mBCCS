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

    @SerializedName("parentShopId")
    @Expose
    private long parentShopId;

    @SerializedName("parent_shop_name")
    @Expose
    private String parentShopName;

    public Shop() {

    }

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

    public long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(long parentShopId) {
        this.parentShopId = parentShopId;
    }

    public String getParentShopName() {
        return parentShopName == null ? "" : parentShopName;
    }

    public void setParentShopName(String parentShopName) {
        this.parentShopName = parentShopName;
    }

    @Override
    public String toString() {
        return shopName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.shopId);
        dest.writeString(this.shopCode);
        dest.writeString(this.shopPath);
        dest.writeString(this.address);
        dest.writeString(this.discountPolicy);
        dest.writeString(this.province);
        dest.writeString(this.shopName);
        dest.writeLong(this.channelTypeId);
        dest.writeLong(this.pricePolicy);
        dest.writeString(this.createDate);
        dest.writeString(this.status);
        dest.writeLong(this.parentShopId);
        dest.writeString(this.parentShopName);
    }

    protected Shop(Parcel in) {
        this.shopId = (Long) in.readValue(Long.class.getClassLoader());
        this.shopCode = in.readString();
        this.shopPath = in.readString();
        this.address = in.readString();
        this.discountPolicy = in.readString();
        this.province = in.readString();
        this.shopName = in.readString();
        this.channelTypeId = in.readLong();
        this.pricePolicy = in.readLong();
        this.createDate = in.readString();
        this.status = in.readString();
        this.parentShopId = in.readLong();
        this.parentShopName = in.readString();
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
