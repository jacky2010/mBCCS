package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetStaffToAssignTaskRequest {

    @SerializedName("shopId ")
    @Expose
    private String shopId;
    @SerializedName("channelTypeId")
    @Expose
    private String channelTypeId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(String channelTypeId) {
        this.channelTypeId = channelTypeId;
    }
}
