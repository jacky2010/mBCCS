package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.config.Config;
import com.viettel.mbccs.data.source.UserRepository;

/**
 * Created by jacky on 6/24/17.
 */

public class AddressRequest <T> extends BaseRequest{

    @Expose
    @SerializedName("sessionId")
    private String mSession;

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

    @Expose
    @SerializedName("teamId")
    private long teamId;

    @Expose
    @SerializedName("dsTeamId")
    private long dsTeamId;

    @Expose
    @SerializedName("hasNIMS")
    private int hasNIMS;

    public AddressRequest() {
        super();
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

    public void setDsTeamId(long dsTeamId) {
        this.dsTeamId = dsTeamId;
    }

    public void setHasNIMS(int hasNIMS) {
        this.hasNIMS = hasNIMS;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
