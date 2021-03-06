package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetSerialsResponse extends DataResponse {

    @SerializedName("serialInStock")
    @Expose
    private StockSerial serialInStock;

    @SerializedName("serialSale")
    @Expose
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
