package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class GetListStockModelRequest  extends BaseRequest  {
    @Expose
    @SerializedName("ownerId")
    private Long ownerId;

    /**
     * if OwnerId == StaffId => OwnerType = 2
     * if OwnerId == ShopId => OwnerType = 1
     */
    @Expose
    @SerializedName("ownerType")
    private Long ownerType;

    @Expose
    @SerializedName("stockTypeId")
    private Long stockTypeId;

    @Expose
    @SerializedName("stockModelId")
    private String stockModelId;

    @Expose
    @SerializedName("stateId")
    private Long stateId;

    @SerializedName("saleTransType")
    @Expose
    private Long saleTransType;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * if OwnerId == StaffId => OwnerType = 2
     * if OwnerId == ShopId => OwnerType = 1
     */
    public Long getOwnerType() {
        return ownerType;
    }

    /**
     * if OwnerId == StaffId => OwnerType = 2
     * if OwnerId == ShopId => OwnerType = 1
     */
    public void setOwnerType(Long ownerType) {
        this.ownerType = ownerType;
    }

    public Long getStockTypeId() {
        return stockTypeId;
    }

    public void setStockTypeId(Long stockTypeId) {
        this.stockTypeId = stockTypeId;
    }

    public String getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(String stockModelId) {
        this.stockModelId = stockModelId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }


    public Long getSaleTransType() {
        return saleTransType;
    }

    public void setSaleTransType(Long saleTransType) {
        this.saleTransType = saleTransType;
    }

    public GetListStockModelRequest() {
        super();
    }


}
