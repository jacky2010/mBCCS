package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SubType;
import java.util.List;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class GetListSubTypeResponse {
    @SerializedName("listSubType")
    @Expose
    private List<SubType> subTypeList;

    public List<SubType> getSubTypeList() {
        return subTypeList;
    }

    public void setSubTypeList(List<SubType> subTypeList) {
        this.subTypeList = subTypeList;
    }
}
