package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class ConnectSubscriberRequest {
    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("contract")
    @Expose
    private Contract contract;

    @SerializedName("subscriber")
    @Expose
    private Subscriber subscriber;

    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    @SerializedName("staffCode")
    @Expose
    private String staffCode;

    @SerializedName("onwerType")
    @Expose
    private String onwerType;

    @SerializedName("ownerId")
    @Expose
    private String ownerId;

    @SerializedName("channelTypeId")
    @Expose
    private String channelTypeId;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
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

    public String getOnwerType() {
        return onwerType;
    }

    public void setOnwerType(String onwerType) {
        this.onwerType = onwerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(String channelTypeId) {
        this.channelTypeId = channelTypeId;
    }
}
