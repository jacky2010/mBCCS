package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 04/07/2017.
 */

public class CreateImportStockRequest {

    @SerializedName("StockTransId")
    private String stockTransId;

    public String getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(String stockTransId) {
        this.stockTransId = stockTransId;
    }
}
