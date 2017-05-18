package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetSerialsReponse {

    @SerializedName("lstSerialSale")
    @Expose
    private List<StockSerial> lstSerialInStock;

    @SerializedName("lstSerialSale")
    @Expose
    private List<StockSerial> lstSerialSale;
}
