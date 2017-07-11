package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ChannelRouterWorkType;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetChannelWorkTypeResponse {

    @SerializedName("lstChannelRouterWorkType")
    @Expose
    private List<ChannelRouterWorkType> mLstChannelRouterWorkType;

    public List<ChannelRouterWorkType> getLstChannelRouterWorkType() {
        return mLstChannelRouterWorkType;
    }

    public void setLstChannelRouterWorkType(List<ChannelRouterWorkType> lstChannelRouterWorkType) {
        this.mLstChannelRouterWorkType = lstChannelRouterWorkType;
    }
}
