package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataResponse {
    @SerializedName("error_code")
    private String errorCode;

    @SerializedName("errorMessage")
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
