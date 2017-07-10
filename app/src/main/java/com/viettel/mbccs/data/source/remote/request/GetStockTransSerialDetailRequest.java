package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 7/8/17.
 */

public class GetStockTransSerialDetailRequest extends BaseRequest {

    @SerializedName("stockTransId")
    @Expose
    private Long stockTransId;

    @SerializedName("stockModelId")
    @Expose
    private Long stockModelId;

    public Long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(Long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public Long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(Long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public GetStockTransSerialDetailRequest() {
        super();
    }
}
