package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;

/**
 * Created by minhnx on 6/7/17.
 */

public class TransferAnyPayRequest {
    @Expose
    private Long fromChannelId;
    @Expose
    private Long toChannelId;
    @Expose
    private Double amount;

    public Long getFromChannelId() {
        return fromChannelId;
    }

    public void setFromChannelId(Long fromChannelId) {
        this.fromChannelId = fromChannelId;
    }

    public Long getToChannelId() {
        return toChannelId;
    }

    public void setToChannelId(Long toChannelId) {
        this.toChannelId = toChannelId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
