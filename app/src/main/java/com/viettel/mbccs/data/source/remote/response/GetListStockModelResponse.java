package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListStockModelResponse {

    @Expose
    @SerializedName("listStockTotal")
    private List<StockTotal> stockTotalList;

    public List<StockTotal> getStockTotalList() {
        return stockTotalList;
    }

    public void setStockTotalList(List<StockTotal> stockTotalList) {
        this.stockTotalList = stockTotalList;
    }
}
