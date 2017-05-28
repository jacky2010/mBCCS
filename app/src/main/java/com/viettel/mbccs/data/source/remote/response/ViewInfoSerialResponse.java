package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class ViewInfoSerialResponse extends DataResponse {

    @Expose
    @SerializedName("stockSerial")
    private StockSerial stockSerial;

    public StockSerial getStockSerial() {
        return stockSerial;
    }

    public void setStockSerial(StockSerial stockSerial) {
        this.stockSerial = stockSerial;
    }
}
