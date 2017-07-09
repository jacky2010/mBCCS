package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/3/17.
 */

public class GetListSearchTransRequest  extends BaseRequest  {

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

    public GetListSearchTransRequest() {
        super();
    }

    public int getShopId() {
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
