package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 7/5/2017.
 */

public class ChannelRouter {
    @SerializedName("channelRouterName")
    @Expose
    private String channelRouterName;
    @SerializedName("channelRouterCode")
    @Expose
    private String channelRouterCode;
    @SerializedName("channelTypeId")
    @Expose
    private Integer channelTypeId;
    @SerializedName("channelRouterId")
    @Expose
    private Integer channelRouterId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getChannelRouterName() {
        return channelRouterName;
    }

    public void setChannelRouterName(String channelRouterName) {
        this.channelRouterName = channelRouterName;
    }

    public String getChannelRouterCode() {
        return channelRouterCode;
    }

    public void setChannelRouterCode(String channelRouterCode) {
        this.channelRouterCode = channelRouterCode;
    }

    public Integer getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(Integer channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public Integer getChannelRouterId() {
        return channelRouterId;
    }

    public void setChannelRouterId(Integer channelRouterId) {
        this.channelRouterId = channelRouterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
