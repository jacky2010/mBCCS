package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class ChannelRouterWorkType {

    @SerializedName("channelRouterWorkTypeName")
    @Expose
    private String channelRouterWorkTypeName;
    @SerializedName("channelRouterWorkTypeCode")
    @Expose
    private String channelRouterWorkTypeCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("listWork")
    @Expose
    private List<Work> listWork = null;
    @SerializedName("channelRouterWorkTypeId")
    @Expose
    private Integer channelRouterWorkTypeId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getChannelRouterWorkTypeName() {
        return channelRouterWorkTypeName;
    }

    public void setChannelRouterWorkTypeName(String channelRouterWorkTypeName) {
        this.channelRouterWorkTypeName = channelRouterWorkTypeName;
    }

    public String getChannelRouterWorkTypeCode() {
        return channelRouterWorkTypeCode;
    }

    public void setChannelRouterWorkTypeCode(String channelRouterWorkTypeCode) {
        this.channelRouterWorkTypeCode = channelRouterWorkTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Work> getListWork() {
        return listWork;
    }

    public void setListWork(List<Work> listWork) {
        this.listWork = listWork;
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
