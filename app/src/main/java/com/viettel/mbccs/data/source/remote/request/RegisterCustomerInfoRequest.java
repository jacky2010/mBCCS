package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Customer;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class RegisterCustomerInfoRequest {

    @SerializedName("isdn")
    @Expose
    private String isdn;

    @SerializedName("serial")
    @Expose
    private String serial;

    @SerializedName("subType")
    @Expose
    private String subType;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
