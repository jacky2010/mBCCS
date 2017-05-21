package com.viettel.mbccs.data.model;

import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelInitData {

    private TelecomServiceAndSaleProgramResponse mTelecomServiceAndSaleProgramResponse;
    private List<ChannelInfo> mChannelInfos;

    public SaleChannelInitData(
            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse,
            List<ChannelInfo> channelInfos) {
        mTelecomServiceAndSaleProgramResponse = telecomServiceAndSaleProgramResponse;
        mChannelInfos = channelInfos;
    }

    public TelecomServiceAndSaleProgramResponse getTelecomServiceAndSaleProgramResponse() {
        return mTelecomServiceAndSaleProgramResponse;
    }

    public void setTelecomServiceAndSaleProgramResponse(
            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse) {
        mTelecomServiceAndSaleProgramResponse = telecomServiceAndSaleProgramResponse;
    }

    public List<ChannelInfo> getChannelInfos() {
        return mChannelInfos;
    }

    public void setChannelInfos(List<ChannelInfo> channelInfos) {
        mChannelInfos = channelInfos;
    }
}
