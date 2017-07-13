package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 13/07/2017.
 */

public class GetListStockModelAllResponse {

        @Expose
        @SerializedName("lstStockTotal")
        private List<StockTotal> stockTotalList;

        public List<StockTotal> getStockTotalList() {
            return stockTotalList;
        }

        public void setStockTotalList(List<StockTotal> stockTotalList) {
            this.stockTotalList = stockTotalList;
        }
}
