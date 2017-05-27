package com.viettel.mbccs.data.model;

import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelInitData {

    private TelecomServiceAndSaleProgramResponse mTelecomServiceAndSaleProgramResponse;
    private GetListChannelByOwnerTypeIdResponse mGetListChannelByOwnerTypeIdResponse;

    public SaleChannelInitData(
            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse,
            GetListChannelByOwnerTypeIdResponse getListChannelByOwnerTypeIdResponse) {
        mTelecomServiceAndSaleProgramResponse = telecomServiceAndSaleProgramResponse;
        mGetListChannelByOwnerTypeIdResponse = getListChannelByOwnerTypeIdResponse;
    }

    public TelecomServiceAndSaleProgramResponse getTelecomServiceAndSaleProgramResponse() {
        return mTelecomServiceAndSaleProgramResponse;
    }

    public void setTelecomServiceAndSaleProgramResponse(
            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse) {
        mTelecomServiceAndSaleProgramResponse = telecomServiceAndSaleProgramResponse;
    }

    public GetListChannelByOwnerTypeIdResponse getmGetListChannelByOwnerTypeIdResponse() {
        return mGetListChannelByOwnerTypeIdResponse;
    }

    public void setGetListChannelByOwnerTypeIdResponse(
            GetListChannelByOwnerTypeIdResponse mGetListChannelByOwnerTypeIdResponse) {
        this.mGetListChannelByOwnerTypeIdResponse = mGetListChannelByOwnerTypeIdResponse;
    }
}
