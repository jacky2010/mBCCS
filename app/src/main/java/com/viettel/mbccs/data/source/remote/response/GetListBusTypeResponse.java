package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.BusType;
import java.util.List;

/**
 * Created by HuyQuyet on 7/4/17.
 */

public class GetListBusTypeResponse {

    @SerializedName("listBusType")
    @Expose
    private List<BusType> busTypeList;

    public List<BusType> getBusTypeList() {
        return busTypeList;
    }

    public void setBusTypeList(List<BusType> busTypeList) {
        this.busTypeList = busTypeList;
    }
}
