package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class GetListOrderRequest extends DataRequest {

    private long shopId;
    private long staffId;
    private long orderStatus;
    private long isdnChannel;
    private String fromDate;
    private String toDate;

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

    public long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getIsdnChannel() {
        return isdnChannel;
    }

    public void setIsdnChannel(long isdnChannel) {
        this.isdnChannel = isdnChannel;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
