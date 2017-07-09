package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 22/06/2017.
 */

public class CreateExpStockRequest extends BaseRequest {

    @SerializedName("stockTransId")
    private long stockTransId;

    @SerializedName("staffId")
    @Expose
    private Long staffId;

    @SerializedName("lstStockSerial")
    private List<StockSerial> mStockSerials;

    public long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(long stockTransId) {
        this.stockTransId = stockTransId;
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

    public CreateExpStockRequest() {
        super();
    }
}
