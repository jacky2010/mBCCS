package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.DistrictResponse;
import java.util.List;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class GetDistrictResponse extends DataResponse {

    @SerializedName("lstDistrict")
    @Expose
    private List<DistrictResponse> districtList;

    public List<DistrictResponse> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictResponse> districtList) {
        this.districtList = districtList;
    }
}
