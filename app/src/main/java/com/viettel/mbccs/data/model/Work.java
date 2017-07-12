package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Da Qiao on 7/12/2017.
 */

public class Work {

    @SerializedName("channelRouterWorkId")
    @Expose
    private Integer channelRouterWorkId;
    @SerializedName("channelRouterWorkName")
    @Expose
    private String channelRouterWorkName;
    @SerializedName("channelRouterWorkTypeId")
    @Expose
    private Integer channelRouterWorkTypeId;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getChannelRouterWorkId() {
        return channelRouterWorkId;
    }

    public void setChannelRouterWorkId(Integer channelRouterWorkId) {
        this.channelRouterWorkId = channelRouterWorkId;
    }

    public String getChannelRouterWorkName() {
        return channelRouterWorkName;
    }

    public void setChannelRouterWorkName(String channelRouterWorkName) {
        this.channelRouterWorkName = channelRouterWorkName;
    }

    public Integer getChannelRouterWorkTypeId() {
        return channelRouterWorkTypeId;
    }

    public void setChannelRouterWorkTypeId(Integer channelRouterWorkTypeId) {
        this.channelRouterWorkTypeId = channelRouterWorkTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
