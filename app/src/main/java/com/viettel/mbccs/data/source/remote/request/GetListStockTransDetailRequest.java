package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public class GetListStockTransDetailRequest {

    @SerializedName("stockTransId")
    @Expose
    private Long stockTransId;

    public Long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(Long stockTransId) {
        this.stockTransId = stockTransId;
    }
}
