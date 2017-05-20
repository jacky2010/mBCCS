package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetSerialRequest implements Serializable{


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

    @SerializedName("channelTypeId")
    @Expose
    private long channelTypeId;

    @SerializedName("language")
    @Expose
    private String language;

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

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
