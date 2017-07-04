package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;

/**
 * Created by minhnx on 6/7/17.
 */

public class RefillAnyPayRequest {
    @Expose
    private Long channelId;
    @Expose
    private String isdn;
    @Expose
    private Double amount;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
