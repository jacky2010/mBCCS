package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListSerialResponse extends DataResponse {

    @Expose
    @SerializedName("serialInStock")
    private StockSerial serialInStock;
    @Expose
    @SerializedName("serialSale")
    private StockSerial serialSale;

    public StockSerial getSerialInStock() {
        return serialInStock;
    }

    public void setSerialInStock(StockSerial serialInStock) {
        this.serialInStock = serialInStock;
    }

    public StockSerial getSerialSale() {
        return serialSale;
    }

    public void setSerialSale(StockSerial serialSale) {
        this.serialSale = serialSale;
    }
}
