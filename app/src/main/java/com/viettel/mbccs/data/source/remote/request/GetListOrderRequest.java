package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class GetListOrderRequest  {

    private Long shopId;
    private Long staffId;
    private String orderStatus;
    private String isdnChannel;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getIsdnChannel() {
        return isdnChannel;
    }

    public void setIsdnChannel(String isdnChannel) {
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
