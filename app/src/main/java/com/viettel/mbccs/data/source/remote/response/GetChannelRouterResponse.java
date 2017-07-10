package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ChannelRouter;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetChannelRouterResponse {

    @SerializedName("lstChannelRouter")
    @Expose
    private List<ChannelRouter> lstChannelRouter;

    public List<ChannelRouter> getLstChannelRouter() {
        return lstChannelRouter;
    }

    public void setLstChannelRouter(List<ChannelRouter> lstChannelRouter) {
        this.lstChannelRouter = lstChannelRouter;
    }
}
