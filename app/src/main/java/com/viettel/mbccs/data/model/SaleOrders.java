package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.DateUtils;
import java.util.Locale;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class SaleOrders implements Parcelable {

    @SerializedName("saleOrdersId")
    @Expose
    private long saleOrdersId;

    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;

    @SerializedName("orderName")
    @Expose
    private String oderName;

    @SerializedName("shopId")
    @Expose
    private long shopId;

    @SerializedName("staffId")
    @Expose
    private long staffId;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("channelId")
    @Expose
    private long channelId;

    @SerializedName("channelCode")
    @Expose
    private String channelCode;

    @SerializedName("channelName")
    @Expose
    private String channelName;

    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    @SerializedName("actionDate")
    @Expose
    private String actionDate;

    public SaleOrders() {
    }

    protected SaleOrders(Parcel in) {
        saleOrdersId = in.readLong();
        orderStatus = in.readString();
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
        dest.writeString(orderStatus);
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
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

    public String getFormatOrderDate() {
        return DateUtils.convertDateToString(
                DateUtils.timeToLong(orderDate, DateUtils.DATE_TIME_FORMAT, Locale.getDefault()),
                DateUtils.DATE_FORMAT1);
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
