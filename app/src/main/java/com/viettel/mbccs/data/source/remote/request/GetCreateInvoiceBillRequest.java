package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 7/4/17.
 */

public class GetCreateInvoiceBillRequest extends BaseRequest {
    @Expose
    @SerializedName("shopId")
    public int mShopId;

    @Expose
    @SerializedName("staffId")
    public long mStaffId;

    @Expose
    @SerializedName("lstSaleTrans")
    public List<SaleTrans> mListSaleTrans;

    public List<SaleTrans> getListSaleTransFromChoose(List<Long> mList) {
        List<SaleTrans> mListSaleTrans = new ArrayList<>();
        for (Long data : mList) {
            mListSaleTrans.add(new SaleTrans(data));
        }
        return mListSaleTrans;
    }

    public GetCreateInvoiceBillRequest() {
        super();
    }

    public class SaleTrans {
        @Expose
        @SerializedName("saleTransId")
        public long mSaleTransId;

        public SaleTrans(long mSaleTransId) {
            this.mSaleTransId = mSaleTransId;
        }
    }
}
