package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/18/17.
 */

public class SerialPickerModel implements Serializable {

    @SerializedName("StockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("Quantity")
    @Expose
    private long quantity;

    @SerializedName("StockMoldeName")
    @Expose
    private String stockMoldeName;

    @SerializedName("lstSerial")
    @Expose
    private List<SerialBO> lstSerial;

    public SerialPickerModel() {
        lstSerial = new ArrayList<>();
        stockMoldeName = "";
    }

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStockMoldeName() {
        return stockMoldeName;
    }

    public void setStockMoldeName(String stockMoldeName) {
        this.stockMoldeName = stockMoldeName;
    }

    public List<SerialBO> getLstSerial() {
        return lstSerial;
    }

    public void setLstSerial(List<SerialBO> lstSerial) {
        this.lstSerial = lstSerial;
    }
}
