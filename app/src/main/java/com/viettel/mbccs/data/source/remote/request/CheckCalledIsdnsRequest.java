package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class CheckCalledIsdnsRequest {

    @SerializedName("isdn")
    @Expose
    private String isdn;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("listIsdn")
    @Expose
    private List<String> listIsdn;

    public List<String> getListIsdn() {
        return listIsdn;
    }

    public void setListIsdn(List<String> listIsdn) {
        this.listIsdn = listIsdn;
    }
}
