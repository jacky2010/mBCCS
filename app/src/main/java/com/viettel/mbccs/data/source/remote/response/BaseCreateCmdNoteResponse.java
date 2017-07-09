package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTrans;

/**
 * Created by FRAMGIA\hoang.van.cuong on 07/07/2017.
 */

public class BaseCreateCmdNoteResponse {

    @SerializedName("stockTrans")
    private StockTrans mStockTrans;

    public StockTrans getStockTrans() {
        return mStockTrans;
    }

    public void setStockTrans(StockTrans stockTrans) {
        mStockTrans = stockTrans;
    }
}
