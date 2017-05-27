package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataRequest {
    @Expose
    @SerializedName("sessionId")
    private Session mSession;

    @Expose
    @SerializedName("wsCode")
    private String wsCode;

    @Expose
    @SerializedName("apiKey")
    private String apiKey;

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
}
