package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Quota;
import java.util.List;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class GetQuotaByProductCodeResponse {

    @SerializedName("lstQuota")
    @Expose
    List<Quota> quotaList;

    public List<Quota> getQuotaList() {
        return quotaList;
    }

    public void setQuotaList(List<Quota> quotaList) {
        this.quotaList = quotaList;
    }
}
