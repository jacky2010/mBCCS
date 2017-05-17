package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.base.BaseModel;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BaseResponse<T> {

    @SerializedName("error_code")
    private String errorCode;

    @SerializedName("error_description")
    private String errorDescription;

    @SerializedName("object")
    private Object mObject;

    @SerializedName("wsResponse")
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
