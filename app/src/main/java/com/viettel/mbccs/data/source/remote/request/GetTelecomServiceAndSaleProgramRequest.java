package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;

public class GetTelecomServiceAndSaleProgramRequest {
    @SerializedName("shop_id")
    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}