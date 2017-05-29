package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Area;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListProvinceResponse extends DataResponse {

    @Expose
    @SerializedName("areaList")
    private List<Area> areaList;

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}
