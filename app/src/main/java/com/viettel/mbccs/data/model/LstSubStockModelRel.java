package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class LstSubStockModelRel {

    @SerializedName("partnerId")
    @Expose
    private Integer partnerId;
    @SerializedName("saleServiceId")
    @Expose
    private Integer saleServiceId;
    @SerializedName("saleTransId")
    @Expose
    private Integer saleTransId;
    @SerializedName("sourceId")
    @Expose
    private Integer sourceId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("stockModelId")
    @Expose
    private Integer stockModelId;
    @SerializedName("stockTypeId")
    @Expose
    private Integer stockTypeId;
    @SerializedName("subId")
    @Expose
    private Integer subId;
    @SerializedName("subStockModelRelId")
    @Expose
    private Integer subStockModelRelId;

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getSaleServiceId() {
        return saleServiceId;
    }

    public void setSaleServiceId(Integer saleServiceId) {
        this.saleServiceId = saleServiceId;
    }

    public Integer getSaleTransId() {
        return saleTransId;
    }

    public void setSaleTransId(Integer saleTransId) {
        this.saleTransId = saleTransId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(Integer stockModelId) {
        this.stockModelId = stockModelId;
    }

    public Integer getStockTypeId() {
        return stockTypeId;
    }

    public void setStockTypeId(Integer stockTypeId) {
        this.stockTypeId = stockTypeId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getSubStockModelRelId() {
        return subStockModelRelId;
    }

    public void setSubStockModelRelId(Integer subStockModelRelId) {
        this.subStockModelRelId = subStockModelRelId;
    }
}
