package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import java.util.List;

/**
 * Created by eo_cuong on 7/8/17.
 */

public class CreateExpCmdRequest extends BaseRequest {
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
    @SerializedName("reasonId")
    @Expose
    private Long reasonId;
    @SerializedName("staffId")
    @Expose
    private Long staffId;
    @SerializedName("discountPolicy")
    @Expose
    private Long discountPolicy;

    @SerializedName("lstStockModel")
    @Expose
    private List<CmdStock> mCmdStocks;

    public List<CmdStock> getCmdStocks() {
        return mCmdStocks;
    }

    public void setCmdStocks(List<CmdStock> cmdStocks) {
        mCmdStocks = cmdStocks;
    }

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

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
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

    public CreateExpCmdRequest() {
        super();
    }

    public static class CmdStock {
        @SerializedName("stockModelId")
        @Expose
        private Long stockModelId;
        @SerializedName("quantity")
        @Expose
        private Long quantity;
        @SerializedName("stateId")
        @Expose
        private Long stateId;

        public Long getStockModelId() {
            return stockModelId;
        }

        public void setStockModelId(Long stockModelId) {
            this.stockModelId = stockModelId;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public Long getStateId() {
            return stateId;
        }

        public void setStateId(Long stateId) {
            this.stateId = stateId;
        }

        public void cloneFromStockTotal(StockTotal stockTotal) {
            setStockModelId(stockTotal.getStockModelId());
            setStateId(stockTotal.getStateId());
            setQuantity(Long.valueOf(stockTotal.getCountChoice()));
        }
    }
}
