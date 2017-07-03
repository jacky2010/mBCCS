package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StockModel implements Serializable {

    @SerializedName("stockModelId")
    @Expose
    private Long stockModelId;

    @SerializedName("stockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("stockMoldeName")
    @Expose
    private String stockModelName;

    @SerializedName("stateId")
    @Expose
    private Long stateId;

    @SerializedName("quantityIssue")
    @Expose
    private Long quantity;

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
