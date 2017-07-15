package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class GetApDomainByTypeRequest extends BaseRequest {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("subType")
    @Expose
    private String subType;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public GetApDomainByTypeRequest() {
        super();
    }
}
