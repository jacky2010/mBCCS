package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StockModel implements Serializable {

    @SerializedName("StockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("StockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("StockMoldeName")
    @Expose
    private String stockModelName;

    @SerializedName("StateId")
    @Expose
    private long stateId;

    @SerializedName("Quantity")
    @Expose
    private long quantity;

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public String getStockModelCode() {
        return stockModelCode;
    }

    public void setStockModelCode(String stockModelCode) {
        this.stockModelCode = stockModelCode;
    }

    public String getStockModelName() {
        return stockModelName;
    }

    public void setStockModelName(String stockModelName) {
        this.stockModelName = stockModelName;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
