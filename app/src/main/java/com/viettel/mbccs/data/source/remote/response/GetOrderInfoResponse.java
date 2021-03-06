package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import java.util.List;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetOrderInfoResponse{
    @Expose
    @SerializedName("lstSaleOrderDetail")
    private List<SaleOrdersDetail> saleOrdersDetailList;

//    @Expose
//    @SerializedName("saleTransInfo")
//    private SaleTrans saleTrans;

    public List<SaleOrdersDetail> getSaleOrdersDetailList() {
        return saleOrdersDetailList;
    }

    public void setSaleOrdersDetailList(List<SaleOrdersDetail> saleOrdersDetailList) {
        this.saleOrdersDetailList = saleOrdersDetailList;
    }

//    public SaleTrans getSaleTrans() {
//        return saleTrans;
//    }
//
//    public void setSaleTrans(SaleTrans saleTrans) {
//        this.saleTrans = saleTrans;
//    }
}
