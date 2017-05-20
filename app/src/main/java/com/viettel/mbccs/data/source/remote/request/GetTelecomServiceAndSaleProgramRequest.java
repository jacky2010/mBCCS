package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GetTelecomServiceAndSaleProgramRequest implements Serializable{
    @SerializedName("shop_id")
    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
