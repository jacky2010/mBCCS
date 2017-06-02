package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ProvinceResponse;
import java.util.List;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class GetProvinceResponse extends DataResponse {

    @SerializedName("lstProvince")
    @Expose
    private List<ProvinceResponse> provinceList;

    public List<ProvinceResponse> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceResponse> provinceList) {
        this.provinceList = provinceList;
    }
}
