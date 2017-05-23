package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.OrderStatus;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class SaleOrders implements Parcelable {

    @SerializedName("SaleOrdersId")
    @Expose
    private long saleOrdersId;

    @SerializedName("OrderStatus")
    @Expose
    private long orderStatus;

    @SerializedName("OrderName")
    @Expose
    private String oderName;

    @SerializedName("ShopId")
    @Expose
    private long shopId;

    @SerializedName("StaffId")
    @Expose
    private long staffId;

    @SerializedName("StaffCode")
    @Expose
    private String staffCode;

    @SerializedName("ChannelId")
    @Expose
    private long channelId;

    @SerializedName("ChannelCode")
    @Expose
    private String channelCode;

    @SerializedName("ChannelName")
    @Expose
    private String channelName;

    @SerializedName("OrderDate")
    @Expose
    private String orderDate;

    @SerializedName("ActionDate")
    @Expose
    private String actionDate;

    public SaleOrders() {
    }

    protected SaleOrders(Parcel in) {
        saleOrdersId = in.readLong();
        orderStatus = in.readLong();
        oderName = in.readString();
        shopId = in.readLong();
        staffId = in.readLong();
        staffCode = in.readString();
        channelId = in.readLong();
        channelCode = in.readString();
        channelName = in.readString();
        orderDate = in.readString();
        actionDate = in.readString();
    }

    public static final Creator<SaleOrders> CREATOR = new Creator<SaleOrders>() {
        @Override
        public SaleOrders createFromParcel(Parcel in) {
            return new SaleOrders(in);
        }

        @Override
        public SaleOrders[] newArray(int size) {
            return new SaleOrders[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(saleOrdersId);
        dest.writeLong(orderStatus);
        dest.writeString(oderName);
        dest.writeLong(shopId);
        dest.writeLong(staffId);
        dest.writeString(staffCode);
        dest.writeLong(channelId);
        dest.writeString(channelCode);
        dest.writeString(channelName);
        dest.writeString(orderDate);
        dest.writeString(actionDate);
    }

    public long getSaleOrdersId() {
        return saleOrdersId;
    }



    public void setSaleOrdersId(long saleOrdersId) {
        this.saleOrdersId = saleOrdersId;
    }

    public long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOderName() {
        return oderName;
    }

    public void setOderName(String oderName) {
        this.oderName = oderName;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }




}
