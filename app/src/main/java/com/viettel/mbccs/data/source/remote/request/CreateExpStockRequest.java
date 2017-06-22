package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 22/06/2017.
 */

public class CreateExpStockRequest {

    @SerializedName("stockTransId")
    private long stockTransId;

    @SerializedName("lstStockSerial")
    private List<StockSerial> mStockSerials;

    public long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public List<StockSerial> getStockSerials() {
        return mStockSerials;
    }

    public void setStockSerials(List<StockSerial> stockSerials) {
        mStockSerials = stockSerials;
    }
}
