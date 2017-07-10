package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class LstAPStockModelBean {

    @SerializedName("checkSerial")
    @Expose
    private Integer checkSerial;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameParam")
    @Expose
    private String nameParam;
    @SerializedName("stockModelCode")
    @Expose
    private String stockModelCode;
    @SerializedName("stockModelId")
    @Expose
    private Integer stockModelId;
    @SerializedName("unit")
    @Expose
    private String unit;

    public Integer getCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(Integer checkSerial) {
        this.checkSerial = checkSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public String getStockModelCode() {
        return stockModelCode;
    }

    public void setStockModelCode(String stockModelCode) {
        this.stockModelCode = stockModelCode;
    }

    public Integer getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(Integer stockModelId) {
        this.stockModelId = stockModelId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
