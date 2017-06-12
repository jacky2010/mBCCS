
package com.viettel.mbccs.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("api")
    @Expose
    private List<Apis> api = null;

    @SerializedName("application")
    @Expose
    private List<Application> application = null;

    @SerializedName("function")
    @Expose
    private List<Function> function = null;

    @SerializedName("role")
    @Expose
    private List<Role> role = null;

    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("userName")
    @Expose
    private String userName;

    public List<Apis> getApi() {
        return api;
    }

    public void setApi(List<Apis> api) {
        this.api = api;
    }

    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }

    public List<Function> getFunction() {
        return function;
    }

    public void setFunction(List<Function> function) {
        this.function = function;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
