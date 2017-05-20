
package com.viettel.mbccs.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("sesssionId")
    @Expose
    private String sesssionId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("domain")
    @Expose
    private Domain domain;
    @SerializedName("application")
    @Expose
    private List<Application> application = null;
    @SerializedName("function")
    @Expose
    private List<Function> function = null;
    @SerializedName("role")
    @Expose
    private List<Role> role = null;
    @SerializedName("api")
    @Expose
    private List<Apis> api = null;
    @SerializedName("token")
    @Expose
    private String token;

    public String getSesssionId() {
        return sesssionId;
    }

    public void setSesssionId(String sesssionId) {
        this.sesssionId = sesssionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
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

    public List<Apis> getApi() {
        return api;
    }

    public void setApi(List<Apis> api) {
        this.api = api;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
