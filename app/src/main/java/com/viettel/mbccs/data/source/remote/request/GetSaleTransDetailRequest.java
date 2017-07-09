package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/4/17.
 */

public class GetSaleTransDetailRequest  extends BaseRequest {
    @Expose
    @SerializedName("saleTransId")
    public long mSaleTransId;

    public GetSaleTransDetailRequest() {
        super();
    }

    public long getSaleTransId() {
        return mSaleTransId;
    }

    public void setSaleTransId(long saleTransId) {
        mSaleTransId = saleTransId;
    }
}
