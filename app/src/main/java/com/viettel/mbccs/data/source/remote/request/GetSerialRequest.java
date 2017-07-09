package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetSerialRequest  extends BaseRequest {

    @SerializedName("ownerId")
    @Expose
    private long ownerId;

    @SerializedName("ownerType")
    @Expose
    private long ownerType;

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("stateId")
    @Expose
    private int stateId;

    @SerializedName("quantity")
    @Expose
    private long quantity;


    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(long ownerType) {
        this.ownerType = ownerType;
    }

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public GetSerialRequest() {
        super();
    }
}
