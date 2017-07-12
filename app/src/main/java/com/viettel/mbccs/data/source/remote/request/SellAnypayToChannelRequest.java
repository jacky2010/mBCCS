package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class SellAnypayToChannelRequest  extends BaseRequest {
    @Expose
    @SerializedName("channelId")
    private int channelId;
    @Expose
    @SerializedName("payMethod")
    private String payMethod;
    @Expose
    @SerializedName("isdnVi")
    private String isdnVi;
    @Expose
    @SerializedName("amount")
    private double amount;
    @Expose
    @SerializedName("staffId")
    private int staffId;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public SellAnypayToChannelRequest() {
        super();
    }
}
