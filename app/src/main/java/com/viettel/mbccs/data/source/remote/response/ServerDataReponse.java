package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 12/06/2017.
 */

public class ServerDataReponse {
    @SerializedName("errorCode")
    private String errorCode;

    @SerializedName("message")
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
