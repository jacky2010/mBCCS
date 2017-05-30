package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginRequest  {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

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
}
