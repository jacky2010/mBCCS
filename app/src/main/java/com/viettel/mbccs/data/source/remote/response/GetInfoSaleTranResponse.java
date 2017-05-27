package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleTrans;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetInfoSaleTranResponse extends DataResponse {

    @Expose
    @SerializedName("saleTrans")
    private SaleTrans saleTrans;

    public SaleTrans getSaleTrans() {
        return saleTrans;
    }

    public void setSaleTrans(SaleTrans saleTrans) {
        this.saleTrans = saleTrans;
    }
}
