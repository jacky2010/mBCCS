package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ApDomain;
import java.util.List;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class GetApDomainResponse extends DataResponse {

    @SerializedName("lstApDomain")
    @Expose
    private List<ApDomain> apDomainList;

    public List<ApDomain> getApDomainList() {
        return apDomainList;
    }

    public void setApDomainList(List<ApDomain> apDomainList) {
        this.apDomainList = apDomainList;
    }
}
