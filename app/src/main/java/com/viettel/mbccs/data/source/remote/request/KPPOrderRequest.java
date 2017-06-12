package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockModel;
import java.util.List;

public class KPPOrderRequest  {

    @SerializedName("staffId")
    @Expose
    private long staffId;

    @SerializedName("channelStaffId")
    @Expose
    private long channelStaffId;

    @SerializedName("lstStockModel")
    @Expose
    private List<StockModel> listStockModel;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public long getChannelStaffId() {
        return channelStaffId;
    }

    public void setChannelStaffId(long channelStaffId) {
        this.channelStaffId = channelStaffId;
    }

    public List<StockModel> getListStockModel() {
        return listStockModel;
    }

    public void setListStockModel(List<StockModel> listStockModel) {
        this.listStockModel = listStockModel;
    }
}
