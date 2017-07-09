package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 7/5/17.
 */

public class GetListExpCmdRequest extends BaseRequest  {

    @SerializedName("fromOwnerId")
    @Expose
    private Long fromOwnerId;
    @SerializedName("fromOwnerType")
    @Expose
    private Long fromOwnerType;
    @SerializedName("toOwnerId")
    @Expose
    private Long toOwnerId;
    @SerializedName("toOwnerType")
    @Expose
    private Long toOwnerType;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("stockTransStatus")
    @Expose
    private Long stockTransStatus;
    @SerializedName("stockTransType")
    @Expose
    private Long stockTransType;

    public Long getFromOwnerId() {
        return fromOwnerId;
    }

    public void setFromOwnerId(Long fromOwnerId) {
        this.fromOwnerId = fromOwnerId;
    }

    public Long getFromOwnerType() {
        return fromOwnerType;
    }

    public void setFromOwnerType(Long fromOwnerType) {
        this.fromOwnerType = fromOwnerType;
    }

    public Long getToOwnerId() {
        return toOwnerId;
    }

    public void setToOwnerId(Long toOwnerId) {
        this.toOwnerId = toOwnerId;
    }

    public Long getToOwnerType() {
        return toOwnerType;
    }

    public void setToOwnerType(Long toOwnerType) {
        this.toOwnerType = toOwnerType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getStockTransStatus() {
        return stockTransStatus;
    }

    public void setStockTransStatus(Long stockTransStatus) {
        this.stockTransStatus = stockTransStatus;
    }

    public Long getStockTransType() {
        return stockTransType;
    }

    public void setStockTransType(Long stockTransType) {
        this.stockTransType = stockTransType;
    }

    public GetListExpCmdRequest() {
        super();
    }
}
