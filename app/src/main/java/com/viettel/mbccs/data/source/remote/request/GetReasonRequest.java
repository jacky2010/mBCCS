package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class GetReasonRequest {

    // Mã kho
    @SerializedName("ownerId")
    @Expose
    private String ownerId;

    // loại kho
    @SerializedName("ownerType")
    @Expose
    private String ownerType;

    // loại reason
    @SerializedName("reasonType")
    @Expose
    private String reasonType;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }
}
