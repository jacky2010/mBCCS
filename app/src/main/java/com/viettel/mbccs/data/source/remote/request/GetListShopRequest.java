package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/23/17.
 */

public class GetListShopRequest   extends BaseRequest  {

    @Expose
    @SerializedName("shopId")
    private long parentShopId;

    public long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(long parentShopId) {
        this.parentShopId = parentShopId;
    }

    public GetListShopRequest() {
        super();
    }
}
