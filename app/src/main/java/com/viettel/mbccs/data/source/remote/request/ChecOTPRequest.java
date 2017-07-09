package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class ChecOTPRequest extends BaseRequest {

    @SerializedName("isdn")
    @Expose
    private String isdn;

    @SerializedName("otp")
    @Expose
    private String otp;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public ChecOTPRequest() {
        super();
    }
}
