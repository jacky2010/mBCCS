package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataVTGRequest {
    @Expose
    @SerializedName("sessionId")
    private Session mSession;

    @Expose
    @SerializedName("apiCode")
    private String apiCode;

    @Expose
    @SerializedName("apiKey")
    private String apiKey;

    public DataVTGRequest() {
        mSession = MBCCSApplication.sessionVTG;
        apiKey = MBCCSApplication.apiKeyVTG;
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
        this.apiKey = apiCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
