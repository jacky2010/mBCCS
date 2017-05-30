package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataRequest<T>  {
    @Expose
    @SerializedName("session")
    private Session mSession;

    @SerializedName("username")
    @Expose
    private String userName;

    @Expose
    @SerializedName("apiCode")
    private String apiCode;

    @Expose
    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("parameterApi")
    @Expose
    private T parameterApi;

    public DataRequest() {
        mSession = MBCCSApplication.session;
        apiKey = MBCCSApplication.apiKey;
    }

    public Session getSession() {
        return mSession;
    }

    public void setSession(Session mSession) {
        this.mSession = mSession;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getParameterApi() {
        return parameterApi;
    }

    public void setParameterApi(T parameterApi) {
        this.parameterApi = parameterApi;
    }


}
