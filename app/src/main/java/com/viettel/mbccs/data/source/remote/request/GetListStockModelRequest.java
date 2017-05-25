package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class GetListStockModelRequest implements Serializable {
    @Expose
    @SerializedName("ownerId")
    private long ownerId;

    @Expose
    @SerializedName("ownerType")
    private long ownerType;

    @Expose
    @SerializedName("stockTypeId")
    private long stockTypeId;

    @Expose
    @SerializedName("stockModelId")
    private String stockModelId;

    @Expose
    @SerializedName("stateId")
    private long stateId;

    private String languate;

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

    public long getStockTypeId() {
        return stockTypeId;
    }

    public void setStockTypeId(long stockTypeId) {
        this.stockTypeId = stockTypeId;
    }

    public String getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(String stockModelId) {
        this.stockModelId = stockModelId;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getLanguate() {
        return languate;
    }

    public void setLanguate(String languate) {
        this.languate = languate;
    }
}
