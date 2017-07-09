package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class GetOTPRequest extends BaseRequest   {
    @SerializedName("isdn")
    @Expose
    private String isdn;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public GetOTPRequest() {
        super();
    }
}
