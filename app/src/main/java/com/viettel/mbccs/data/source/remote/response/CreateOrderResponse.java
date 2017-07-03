package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 03/07/2017.
 */

public class CreateOrderResponse {

    @SerializedName("orderId")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
