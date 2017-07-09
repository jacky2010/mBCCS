package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 7/8/17.
 */

public class CreateExpNoteRequest extends BaseRequest {
    @SerializedName("stockTransId")
    @Expose
    private Long stockTransId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("staffId")
    @Expose
    private Long staffId;

    public Long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(Long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public CreateExpNoteRequest() {
        super();
    }
}
