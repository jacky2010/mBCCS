package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleTransDetail;

import java.util.List;

/**
 * Created by jacky on 7/4/17.
 */

public class GetSaleTransDetailResponse {
    @SerializedName("lstSaleTransDetail")
    @Expose
    public List<SaleTransDetail> mListSaleTrans;
}
