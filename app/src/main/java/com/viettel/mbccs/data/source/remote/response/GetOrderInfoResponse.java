package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import java.util.List;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetOrderInfoResponse extends DataResponse{
    @Expose
    @SerializedName("lstSaleOrdersDetail")
    private List<SaleOrdersDetail> saleOrdersDetailList;

    @Expose
    @SerializedName("saleTrans")
    private SaleTrans saleTrans;

    public List<SaleOrdersDetail> getSaleOrdersDetailList() {
        return saleOrdersDetailList;
    }

    public void setSaleOrdersDetailList(List<SaleOrdersDetail> saleOrdersDetailList) {
        this.saleOrdersDetailList = saleOrdersDetailList;
    }

    public SaleTrans getSaleTrans() {
        return saleTrans;
    }

    public void setSaleTrans(SaleTrans saleTrans) {
        this.saleTrans = saleTrans;
    }
}
