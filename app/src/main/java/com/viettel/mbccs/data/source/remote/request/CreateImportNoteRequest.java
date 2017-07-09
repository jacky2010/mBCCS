package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 07/07/2017.
 */

public class CreateImportNoteRequest extends BaseRequest {
    @SerializedName("stockTransId")
    @Expose
    private Long stockTransId;
    @SerializedName("staffId")
    @Expose
    private Long staffId;

    public Long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(Long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public CreateImportNoteRequest() {
        super();
    }
}
