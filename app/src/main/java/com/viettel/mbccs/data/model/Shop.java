package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class Shop implements Parcelable {
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

    public Shop() {

    }

    protected Shop(Parcel in) {
        shopId = in.readLong();
        shopCode = in.readString();
        shopName = in.readString();
        channelTypeId = in.readLong();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(shopId);
        dest.writeString(shopCode);
        dest.writeString(shopName);
        dest.writeLong(channelTypeId);
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
}
