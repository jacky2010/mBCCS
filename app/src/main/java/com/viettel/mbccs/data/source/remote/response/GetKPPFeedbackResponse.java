package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.KPPFeedback;

import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetKPPFeedbackResponse extends DataResponse {
    @Expose
    @SerializedName("listFeedback")
    private List<KPPFeedback> listFeedback;

    public List<KPPFeedback> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<KPPFeedback> listFeedback) {
        this.listFeedback = listFeedback;
    }
}
