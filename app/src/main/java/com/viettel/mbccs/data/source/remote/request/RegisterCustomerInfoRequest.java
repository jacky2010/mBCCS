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
}
