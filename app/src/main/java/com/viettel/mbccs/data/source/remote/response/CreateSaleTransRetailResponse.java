package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/28/17.
 */

public class CreateSaleTransRetailResponse extends DataResponse {

    @SerializedName("saleTransId")
    @Expose
    private String saleTransId;

    public String getSaleTransId() {
        return saleTransId;
    }

    public void setSaleTransId(String saleTransId) {
        this.saleTransId = saleTransId;
    }
}
