package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class CreateSaleTransFromOrderResponse extends DataResponse {

    @SerializedName("saleTransId")
    @Expose
    private long saleTransId;

    public long getSaleTransId() {
        return saleTransId;
    }

    public void setSaleTransId(long saleTransId) {
        this.saleTransId = saleTransId;
    }
}
