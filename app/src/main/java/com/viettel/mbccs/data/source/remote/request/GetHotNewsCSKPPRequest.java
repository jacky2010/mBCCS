package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetHotNewsCSKPPRequest extends BaseRequest {
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("ownerCode")
    private String ownerCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public GetHotNewsCSKPPRequest() {
        super();
    }
}
