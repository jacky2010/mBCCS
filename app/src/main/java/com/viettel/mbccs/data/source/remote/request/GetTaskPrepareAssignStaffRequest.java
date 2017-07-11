package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/23/2017.
 */

public class GetTaskPrepareAssignStaffRequest extends BaseRequest{

    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("account")
    @Expose
    private String account;

    @SerializedName("startDate")
    @Expose
    private String fromDate;

    @SerializedName("endDate")
    @Expose
    private String toDate;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("status")
    @Expose
    private String status;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
