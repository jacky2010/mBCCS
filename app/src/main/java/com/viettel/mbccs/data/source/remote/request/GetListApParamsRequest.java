package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/15/17.
 */

public class GetListApParamsRequest {
    @SerializedName("type")
    @Expose
    public String mType;
}
