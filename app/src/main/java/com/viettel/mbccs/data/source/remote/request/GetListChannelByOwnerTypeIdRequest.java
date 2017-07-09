package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetListChannelByOwnerTypeIdRequest extends BaseRequest {

    public Long staffId;
    public Long channelTypeId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public GetListChannelByOwnerTypeIdRequest() {
        super();
    }
}
