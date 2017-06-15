package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleOrders;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListOrderResponse{
    @Expose
    @SerializedName("lstSaleOrder")
    private List<SaleOrders> saleOrdersList;

    public List<SaleOrders> getSaleOrdersList() {
        return saleOrdersList;
    }

    public void setSaleOrdersList(List<SaleOrders> saleOrdersList) {
        this.saleOrdersList = saleOrdersList;
    }
}
