package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class UpdateSaleOrderRequest  extends BaseRequest {

    @SerializedName("saleOrderId")
    @Expose
    private String saleOrderId;

    @SerializedName("newStatus")
    @Expose
    private String newStatus;

    @SerializedName("reasonId")
    @Expose
    private String reasonId;

    @SerializedName("saleTransId")
    @Expose
    private long saleTransId;

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public long getSaleTransId() {
        return saleTransId;
    }

    public void setSaleTransId(long saleTransId) {
        this.saleTransId = saleTransId;
    }

    public UpdateSaleOrderRequest() {
        super();
    }
}
