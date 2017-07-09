package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetHotNewsInfoCSKPPRequest extends BaseRequest {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("hotNewsId")
    @Expose
    private int hotNewsId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getHotNewsId() {
        return hotNewsId;
    }

    public void setHotNewsId(int hotNewsId) {
        this.hotNewsId = hotNewsId;
    }
}
