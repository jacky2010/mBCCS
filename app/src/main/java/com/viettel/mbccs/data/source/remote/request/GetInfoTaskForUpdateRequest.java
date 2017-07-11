package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.TaskShopManagement;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetInfoTaskForUpdateRequest extends BaseRequest{

    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("type")
    @Expose
    @TaskShopManagement.TaskType
    private Integer type;

    @SerializedName("account")
    @Expose
    private String account;

    @SerializedName("fromDate")
    @Expose
    private String fromDate;

    @SerializedName("toDate")
    @Expose
    private String toDate;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("status")
    @Expose
    private Integer status;

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

    @TaskShopManagement.TaskType
    public Integer getType() {
        return type;
    }

    public void setType(@TaskShopManagement.TaskType int type) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
