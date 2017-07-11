package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class CurrBillCycle {

    @SerializedName("billCycleCode")
    @Expose
    private Long billCycleCode;

    @SerializedName("name")
    @Expose
    private String name;

    public Long getBillCycleCode() {
        return billCycleCode;
    }

    public void setBillCycleCode(Long billCycleCode) {
        this.billCycleCode = billCycleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
