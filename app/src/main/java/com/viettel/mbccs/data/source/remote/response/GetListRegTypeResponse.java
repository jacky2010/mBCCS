package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.RegType;
import java.util.List;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class GetListRegTypeResponse {
    @SerializedName("listRegType")
    @Expose
    private List<RegType> regTypeList;

    public List<RegType> getRegTypeList() {
        return regTypeList;
    }

    public void setRegTypeList(List<RegType> regTypeList) {
        this.regTypeList = regTypeList;
    }
}
