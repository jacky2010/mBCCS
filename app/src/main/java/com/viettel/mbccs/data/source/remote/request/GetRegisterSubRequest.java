package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetRegisterSubRequest {
    @Expose
    @SerializedName("isdn")
    private String isdn;
    @Expose
    @SerializedName("idNo")
    private String idNo;
    @Expose
    @SerializedName("idType")
    private Long idType;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }
}
