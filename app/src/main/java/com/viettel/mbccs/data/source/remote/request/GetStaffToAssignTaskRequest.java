package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetStaffToAssignTaskRequest  extends BaseRequest{

    @SerializedName("shopId")
    @Expose
    private Long shopId;
    @SerializedName("channelTypeId")
    @Expose
    private Long channelTypeId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(Long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }
}
