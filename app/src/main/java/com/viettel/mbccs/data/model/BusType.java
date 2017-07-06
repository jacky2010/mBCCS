package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/4/17.
 */

public class BusType {

    @SerializedName("tet")
    @Expose
    private int tet;

    @SerializedName("tct")
    @Expose
    private int tct;

    @SerializedName("tax")
    @Expose
    private int tax;

    @SerializedName("tinRequired")
    @Expose
    private String tinRequired;

    @SerializedName("expireRequired")
    @Expose
    private String expireRequired;

    @SerializedName("changeDatetime")
    @Expose
    private String changeDatetime;

    @SerializedName("createDatetime")
    @Expose
    private String createDatetime;

    @SerializedName("popRequired")
    @Expose
    private String popRequired;

    @SerializedName("custType")
    @Expose
    private String custType;

    @SerializedName("permitRequired")
    @Expose
    private String permitRequired;

    @SerializedName("idRequired")
    @Expose
    private String idRequired;

    @SerializedName("busType")
    @Expose
    private String busType;

    @SerializedName("busTypeId")
    @Expose
    private Integer busTypeId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("status")
    @Expose
    private int status;

    public int getTet() {
        return tet;
    }

    public void setTet(int tet) {
        this.tet = tet;
    }

    public int getTct() {
        return tct;
    }

    public void setTct(int tct) {
        this.tct = tct;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getTinRequired() {
        return tinRequired;
    }

    public void setTinRequired(String tinRequired) {
        this.tinRequired = tinRequired;
    }

    public String getExpireRequired() {
        return expireRequired;
    }

    public void setExpireRequired(String expireRequired) {
        this.expireRequired = expireRequired;
    }

    public String getChangeDatetime() {
        return changeDatetime;
    }

    public void setChangeDatetime(String changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getPopRequired() {
        return popRequired;
    }

    public void setPopRequired(String popRequired) {
        this.popRequired = popRequired;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getPermitRequired() {
        return permitRequired;
    }

    public void setPermitRequired(String permitRequired) {
        this.permitRequired = permitRequired;
    }

    public String getIdRequired() {
        return idRequired;
    }

    public void setIdRequired(String idRequired) {
        this.idRequired = idRequired;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public Integer getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(Integer busTypeId) {
        this.busTypeId = busTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getName();
    }
}
