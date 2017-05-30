package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.PrecinctResponse;
import java.util.List;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class GetPrecinctResponse extends DataResponse {

    @SerializedName("lstPrecinct")
    @Expose
    private List<PrecinctResponse> precinctList;

    public List<PrecinctResponse> getPrecinctList() {
        return precinctList;
    }

    public void setPrecinctList(List<PrecinctResponse> precinctList) {
        this.precinctList = precinctList;
    }
}
