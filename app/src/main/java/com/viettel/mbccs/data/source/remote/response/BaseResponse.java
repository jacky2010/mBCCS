package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.base.BaseModel;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BaseResponse<T extends BaseModel> {

    @SerializedName("code")
    private int errorCode;

    @SerializedName("data")
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
