package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewInfoSerialRequest implements Serializable{

    @Expose
    @SerializedName("")
    private long ownerId;

    @Expose
    @SerializedName("")
    private long ownerType;

    @Expose
    @SerializedName("")
    private long stockModelId;

    @Expose
    @SerializedName("")
    private long stateId;

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

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }
}
