package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetTotalStockRequest {

    public static final int SALE_TRAN_RETAIL = 1;
    public static final int SALE_TRAN_CHANNEL = 3;
    public static final int STATE_NEW = 1;
    public static final int STATE_FAIL = 3;

    @SerializedName("ownerId")
    @Expose
    private long ownerId;

    @SerializedName("ownerType")
    @Expose
    private long ownerType;

    @SerializedName("telecomServiceId")
    @Expose
    private long telecomServiceId;

    @SerializedName("saleProgameId")
    @Expose
    private long saleProgameId;

    @SerializedName("saleTransType")
    @Expose
    private int saleTransType;

    @SerializedName("stateId")
    @Expose
    private int stateId;

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

    public long getTelecomServiceId() {
        return telecomServiceId;
    }

    public void setTelecomServiceId(long telecomServiceId) {
        this.telecomServiceId = telecomServiceId;
    }

    public long getSaleProgameId() {
        return saleProgameId;
    }

    public void setSaleProgameId(long saleProgameId) {
        this.saleProgameId = saleProgameId;
    }

    public int getSaleTransType() {
        return saleTransType;
    }

    public void setSaleTransType(int saleTransType) {
        this.saleTransType = saleTransType;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
