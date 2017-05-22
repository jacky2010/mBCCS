package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.base.BaseModel;

import java.io.Serializable;

/**
 * Created by minhnx on 5/19/17.
 */

public class ChangeSimItem extends BaseModel implements Serializable{
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("sim_info")
    @Expose
    private ChangeSimInfo changeSimInfo;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ChangeSimInfo getChangeSimInfo() {
        return changeSimInfo;
    }

    public void setChangeSimInfo(ChangeSimInfo changeSimInfo) {
        this.changeSimInfo = changeSimInfo;
    }
}
