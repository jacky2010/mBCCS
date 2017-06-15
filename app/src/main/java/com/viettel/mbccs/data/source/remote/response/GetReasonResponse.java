package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Reason;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetReasonResponse extends DataResponse {

    @Expose
    @SerializedName("listReason")
    private List<Reason> reasonList;

    public List<Reason> getReasonList() {
        return reasonList;
    }

    public void setReasonList(List<Reason> reasonList) {
        this.reasonList = reasonList;
    }
}
