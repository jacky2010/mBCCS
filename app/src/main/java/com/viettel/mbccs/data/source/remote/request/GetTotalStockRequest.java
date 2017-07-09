package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetTotalStockRequest  extends BaseRequest {

    public static final Integer SALE_TRAN_RETAIL = 1;
    public static final Integer SALE_TRAN_CHANNEL = 3;
    public static final Integer STATE_NEW = 1;
    public static final Integer STATE_FAIL = 3;

    @SerializedName("ownerId")
    @Expose
    private Long ownerId;

    @SerializedName("ownerType")
    @Expose
    private Long ownerType;

    @SerializedName("telecomserviceId")
    @Expose
    private Long telecomServiceId;

    @SerializedName("saleProgameId")
    @Expose
    private Long saleProgameId;

    @SerializedName("saleTransType")
    @Expose
    private Long saleTransType;

    @SerializedName("stateId")
    @Expose
    private Long stateId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Long ownerType) {
        this.ownerType = ownerType;
    }

    public Long getTelecomServiceId() {
        return telecomServiceId;
    }

    public void setTelecomServiceId(Long telecomServiceId) {
        this.telecomServiceId = telecomServiceId;
    }

    public Long getSaleProgameId() {
        return saleProgameId;
    }

    public void setSaleProgameId(Long saleProgameId) {
        this.saleProgameId = saleProgameId;
    }

    public Long getSaleTransType() {
        return saleTransType;
    }

    public void setSaleTransType(Long saleTransType) {
        this.saleTransType = saleTransType;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public GetTotalStockRequest() {
        super();
    }
}
