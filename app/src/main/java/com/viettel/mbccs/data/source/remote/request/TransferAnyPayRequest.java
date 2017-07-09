package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class TransferAnyPayRequest extends BaseRequest {
    @Expose
    @SerializedName("fromChannelId")
    private Integer fromChannelId;
    @Expose
    @SerializedName("toChannelId")
    private Integer toChannelId;
    @Expose
    @SerializedName("amount")
    private Double amount;

    public Integer getFromChannelId() {
        return fromChannelId;
    }

    public void setFromChannelId(Integer fromChannelId) {
        this.fromChannelId = fromChannelId;
    }

    public Integer getToChannelId() {
        return toChannelId;
    }

    public void setToChannelId(Integer toChannelId) {
        this.toChannelId = toChannelId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransferAnyPayRequest() {
        super();
    }
}
