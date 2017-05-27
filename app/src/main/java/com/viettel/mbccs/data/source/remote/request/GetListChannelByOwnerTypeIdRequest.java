package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetListChannelByOwnerTypeIdRequest extends DataRequest {
    public long shopId;
    public long staffId;
    public long channelTypeId;
    public String language;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
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
