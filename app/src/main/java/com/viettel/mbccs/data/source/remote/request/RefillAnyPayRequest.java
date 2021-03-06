package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class RefillAnyPayRequest  extends BaseRequest {
    @Expose
    @SerializedName("channelId")
    private Long channelId;
    @Expose
    @SerializedName("amount")
    private Double amount;
    @Expose
    @SerializedName("isdn")
    private String isdn;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public RefillAnyPayRequest() {
        super();
    }
}
