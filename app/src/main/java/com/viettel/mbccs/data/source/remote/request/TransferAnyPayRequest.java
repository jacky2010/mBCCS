package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class TransferAnyPayRequest {
    @Expose
    @SerializedName("fromISDN")
    private String fromIsdn;
    @Expose
    @SerializedName("toISDN")
    private String toIsdn;
    @Expose
    private Double amount;

    public String getFromIsdn() {
        return fromIsdn;
    }

    public void setFromIsdn(String fromIsdn) {
        this.fromIsdn = fromIsdn;
    }

    public String getToIsdn() {
        return toIsdn;
    }

    public void setToIsdn(String toIsdn) {
        this.toIsdn = toIsdn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
