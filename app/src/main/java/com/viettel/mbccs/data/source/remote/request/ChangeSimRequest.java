package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class ChangeSimRequest extends BaseRequest {

    @SerializedName("isdn")
    @Expose
    private String isdn;
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("subType")
    @Expose
    private String subType;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public ChangeSimRequest() {
        super();
    }
}
