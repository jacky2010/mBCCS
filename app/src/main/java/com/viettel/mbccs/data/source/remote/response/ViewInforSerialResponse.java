package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 06/07/2017.
 */

public class ViewInforSerialResponse {

    @SerializedName("stockSerials")
    @Expose
    List<StockSerial> mStockSerials;

    public List<StockSerial> getStockSerials() {
        return mStockSerials;
    }

    public void setStockSerials(List<StockSerial> stockSerials) {
        mStockSerials = stockSerials;
    }
}
