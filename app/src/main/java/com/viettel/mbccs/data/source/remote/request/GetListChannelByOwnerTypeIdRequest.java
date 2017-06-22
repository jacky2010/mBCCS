package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetListChannelByOwnerTypeIdRequest {

    public String staffId;
    public Long channelTypeId;
    public String language;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
