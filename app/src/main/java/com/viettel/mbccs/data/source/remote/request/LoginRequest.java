package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.config.Config;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginRequest extends BaseRequest  {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("appCode")
    private String appCode;

    public LoginRequest() {
        super();
        prefix = Config.PREFIX;
        appCode = Config.APPCODE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
