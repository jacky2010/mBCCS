package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BaseRequest<T> {

    @SerializedName("sessionId")
    private Session mSession;

    @SerializedName("wsCode")
    private String wsCode;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("wsRequest")
    private T request;

    public Session getSession() {
        return mSession;
    }

    public void setSession(Session mSession) {
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

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
