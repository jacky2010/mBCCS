package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class SellAnypayToCustomerRequest extends BaseRequest {

    @Expose
    @SerializedName("isdn")
    private String isdn;
    @Expose
    @SerializedName("paymethod")
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

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
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

    public SellAnypayToCustomerRequest() {
        super();
    }
}
