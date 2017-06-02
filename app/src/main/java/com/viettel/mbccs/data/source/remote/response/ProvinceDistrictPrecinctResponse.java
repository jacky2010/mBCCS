package com.viettel.mbccs.data.source.remote.response;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class ProvinceDistrictPrecinctResponse {
    private GetProvinceResponse getProvinceResponse;
    private GetDistrictResponse getDistrictResponse;
    private GetPrecinctResponse getPrecinctResponse;

    public ProvinceDistrictPrecinctResponse(GetProvinceResponse getProvinceResponse,
            GetDistrictResponse getDistrictResponse, GetPrecinctResponse getPrecinctResponse) {
        this.getProvinceResponse = getProvinceResponse;
        this.getDistrictResponse = getDistrictResponse;
        this.getPrecinctResponse = getPrecinctResponse;
    }

    public GetProvinceResponse getGetProvinceResponse() {
        return getProvinceResponse;
    }

    public void setGetProvinceResponse(GetProvinceResponse getProvinceResponse) {
        this.getProvinceResponse = getProvinceResponse;
    }

    public GetDistrictResponse getGetDistrictResponse() {
        return getDistrictResponse;
    }

    public void setGetDistrictResponse(GetDistrictResponse getDistrictResponse) {
        this.getDistrictResponse = getDistrictResponse;
    }

    public GetPrecinctResponse getGetPrecinctResponse() {
        return getPrecinctResponse;
    }

    public void setGetPrecinctResponse(GetPrecinctResponse getPrecinctResponse) {
        this.getPrecinctResponse = getPrecinctResponse;
    }
}
