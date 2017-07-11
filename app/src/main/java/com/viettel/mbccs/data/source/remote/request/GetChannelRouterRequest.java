package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetChannelRouterRequest extends BaseRequest{

    @SerializedName("channelTypeId")
    @Expose
    private String channelTypeId;

    public String getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(String channelTypeId) {
        this.channelTypeId = channelTypeId;
    }
}
