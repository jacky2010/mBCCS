package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.ReasonType;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class GetReasonRequest  extends BaseRequest  {

    // loại reason
    @ReasonType
    @SerializedName("reasonType")
    @Expose
    private String reasonType;

    @ReasonType
    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(@ReasonType String reasonType) {
        this.reasonType = reasonType;
    }

    public GetReasonRequest() {
        super();
    }
}
