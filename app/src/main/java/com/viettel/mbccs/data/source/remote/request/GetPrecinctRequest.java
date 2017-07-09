package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class GetPrecinctRequest  extends BaseRequest  {

    @SerializedName("provinceCode")
    @Expose
    private String provinceId;

    @SerializedName("districtCode")
    @Expose
    private String districtId;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public GetPrecinctRequest() {
        super();
    }
}
