package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.SaleTranType;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetOrderInfoRequest {
    @Expose
    @SerializedName("saleOrderId")
    private Long saleOrderId;

    @Expose
    @SerializedName("ownerType")
    private String  ownerType;

    @Expose
    @SerializedName("telecomserviceId")
    private String telecomserviceId;

    @Expose
    @SerializedName("ownerId")
    private Long ownerId;

    @SaleTranType
    @Expose
    @SerializedName("saleTransType")
    private Long saleTransType;

    @Expose
    @SerializedName("stateId")
    private String stateId;

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getTelecomserviceId() {
        return telecomserviceId;
    }

    public void setTelecomserviceId(String telecomserviceId) {
        this.telecomserviceId = telecomserviceId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getSaleTransType() {
        return saleTransType;
    }

    public void setSaleTransType(Long saleTransType) {
        this.saleTransType = saleTransType;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
}
