package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTelecomServiceAndSaleProgramRequest  extends BaseRequest {
    @Expose
    @SerializedName("shopId")
    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public GetTelecomServiceAndSaleProgramRequest() {
        super();
    }
}
