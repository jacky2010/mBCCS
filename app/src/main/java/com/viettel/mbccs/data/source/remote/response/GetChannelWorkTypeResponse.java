package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ChannelWorkType;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetChannelWorkTypeResponse {

    @SerializedName("lstChannelWorkType")
    @Expose
    private List<ChannelWorkType> lstChannelWorkType;

    public List<ChannelWorkType> getLstChannelWorkType() {
        return lstChannelWorkType;
    }

    public void setLstChannelWorkType(List<ChannelWorkType> lstChannelWorkType) {
        this.lstChannelWorkType = lstChannelWorkType;
    }
}
