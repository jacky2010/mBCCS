package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SerialBO;
import java.util.List;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class GetSerialsResponse extends DataResponse {

    @SerializedName("lstSerialInStock")
    @Expose
    private List<SerialBO> lstSerialInStock;

    @SerializedName("lstSerialSale")
    @Expose
    private List<SerialBO> lstSerialSale;

    public List<SerialBO> getLstSerialInStock() {
        return lstSerialInStock;
    }

    public void setLstSerialInStock(List<SerialBO> lstSerialInStock) {
        this.lstSerialInStock = lstSerialInStock;
    }

    public List<SerialBO> getLstSerialSale() {
        return lstSerialSale;
    }

    public void setLstSerialSale(List<SerialBO> lstSerialSale) {
        this.lstSerialSale = lstSerialSale;
    }
}
