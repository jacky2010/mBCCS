package com.viettel.mbccs.data.source.remote.response;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ChannelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListChannelByOwnerTypeIdResponse extends DataResponse {

    @Expose
    @SerializedName("lstChannelInfo")
    private List<ChannelInfo> channelInfoList;

    private List<String> mDataChannel = new ArrayList<>();

    public List<ChannelInfo> getChannelInfoList() {
        return channelInfoList;
    }

    public List<String> getListDataChannel() {
        for (ChannelInfo channelInfo : channelInfoList) {
            mDataChannel.add(channelInfo.getChannelName());
        }
        return mDataChannel;
    }

    public void setChannelInfoList(List<ChannelInfo> channelInfoList) {
        this.channelInfoList = channelInfoList;
    }
}
