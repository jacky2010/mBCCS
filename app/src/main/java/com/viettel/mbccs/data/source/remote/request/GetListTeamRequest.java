package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by jacky on 6/23/17.
 */

public class GetListTeamRequest  extends BaseRequest  {
    public String provinceCode;
    public String districtCode;
    public String precinctCode;


    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getPrecinctCode() {
        return precinctCode;
    }

    public void setPrecinctCode(String precinctCode) {
        this.precinctCode = precinctCode;
    }

    public GetListTeamRequest() {
        super();
    }
}
