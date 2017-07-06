package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/3/17.
 */

public class GetListSearchTransRequest {

    @Expose
    @SerializedName("shopId")
    public int mShopId;

    @SerializedName("fromDate")
    @Expose
    public String mFromDate;

    @Expose
    @SerializedName("toDate")
    public String mToDate;

    @Expose
    @SerializedName("staffId")
    public long mStaffId;

    @Expose
    @SerializedName("saleTransType")
    public int mSaleTransType;
}
