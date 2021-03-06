package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.config.Config;
import com.viettel.mbccs.data.source.UserRepository;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataRequest<T> {

    @Expose
    @SerializedName("sessionId")
    private String mSession;

    @SerializedName("username")
    @Expose
    private String userName;

    @Expose
    @SerializedName("wsCode")
    private String wsCode;

    @Expose
    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("wsRequest")
    @Expose
    private T wsRequest;

    public DataRequest() {

        userName =  UserRepository.getInstance().getLoginUserName();
        mSession =  UserRepository.getInstance().getUser().getSessionId();
        apiKey = Config.API_KEY;
        token =  UserRepository.getInstance().getUser().getToken();
    }

    public String getmSession() {
        return mSession;
    }

    public void setmSession(String mSession) {
        this.mSession = mSession;
    }

    public String getWsCode() {
        return wsCode;
    }

    public void setWsCode(String wsCode) {
        this.wsCode = wsCode;
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

    public T getWsRequest() {
        return wsRequest;
    }

    public void setWsRequest(T wsRequest) {
        this.wsRequest = wsRequest;
    }
}
