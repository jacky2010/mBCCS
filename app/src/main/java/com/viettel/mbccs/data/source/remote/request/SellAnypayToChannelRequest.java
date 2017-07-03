package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class SellAnypayToChannelRequest {
    @Expose
    @SerializedName("channelId")
    private Long channelId;
    @Expose
    @SerializedName("payMethod")
    private Integer payMethod;
    @Expose
    @SerializedName("isdnVi")
    private String isdnVi;
    @Expose
    @SerializedName("amount")
    private double amount;
    @Expose
    @SerializedName("staffId")
    private Long staffId;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getIsdnVi() {
        return isdnVi;
    }

    public void setIsdnVi(String isdnVi) {
        this.isdnVi = isdnVi;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
