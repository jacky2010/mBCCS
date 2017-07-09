package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class GetListRegTypeRequest  extends BaseRequest  {

    @SerializedName("subType")
    @Expose
    private String subType;

    @SerializedName("telServiceId")
    @Expose
    private int telServiceId;

    @SerializedName("productCode")
    @Expose
    private String productCode;

    @SerializedName("channelTypeId")
    @Expose
    private String channelTypeId;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getTelServiceId() {
        return telServiceId;
    }

    public void setTelServiceId(int telServiceId) {
        this.telServiceId = telServiceId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(String channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public GetListRegTypeRequest() {
        super();
    }
}
