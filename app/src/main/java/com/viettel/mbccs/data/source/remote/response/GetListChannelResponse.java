package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.ChannelType;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetListChannelResponse {

    @SerializedName("lstChannelType")
    @Expose
    private List<ChannelType> lstChannelType;

    public List<ChannelType> getLstChannelType() {
        return lstChannelType;
    }

    public void setLstChannelType(List<ChannelType> lstChannelType) {
        this.lstChannelType = lstChannelType;
    }
}
