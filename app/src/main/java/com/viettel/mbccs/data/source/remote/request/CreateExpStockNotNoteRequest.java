package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 29/06/2017.
 */

public class CreateExpStockNotNoteRequest {

    @SerializedName("fromOwnerId")
    private Long fromOwnerId;

    @SerializedName("fromOwnerType")
    private Long fromOwnerType;

    @SerializedName("toOwnerId")
    private Long toOwnerId;

    @SerializedName("toOwnerType")
    private Long toOwnerType;

    @SerializedName("stockTransCode")
    private Long stockTransCode;

    @SerializedName("reasonId")
    private Long reasonId;

    @SerializedName("staffId")
    private Long staffId;

    @SerializedName("discountPolicy")
    private Long discountPolicy;

    @SerializedName("lstStockSerial")
    private List<StockSerial> mStockSerials;

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

    public Long getStockTransCode() {
        return stockTransCode;
    }

    public void setStockTransCode(Long stockTransCode) {
        this.stockTransCode = stockTransCode;
    }

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public List<StockSerial> getStockSerials() {
        return mStockSerials;
    }

    public void setStockSerials(List<StockSerial> stockSerials) {
        mStockSerials = stockSerials;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(Long discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public CreateExpStockNotNoteRequest() {
        super();
    }
}
