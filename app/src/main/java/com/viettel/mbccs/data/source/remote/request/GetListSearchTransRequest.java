package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/3/17.
 */

public class GetListSearchTransRequest  extends BaseRequest  {

    @Expose
    @SerializedName("shopId")
    public long mShopId;

    @SerializedName("startDate")
    @Expose
    public String mFromDate;

    @Expose
    @SerializedName("endDate")
    public String mToDate;

    public long mStaffId;

    public int mSaleTransType;

    public GetListSearchTransRequest() {
        super();
    }

    public long getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String fromDate) {
        mFromDate = fromDate;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String toDate) {
        mToDate = toDate;
    }

    public long getStaffId() {
        return mStaffId;
    }

    public void setStaffId(long staffId) {
        mStaffId = staffId;
    }

    public int getSaleTransType() {
        return mSaleTransType;
    }

    public void setSaleTransType(int saleTransType) {
        mSaleTransType = saleTransType;
    }
}
