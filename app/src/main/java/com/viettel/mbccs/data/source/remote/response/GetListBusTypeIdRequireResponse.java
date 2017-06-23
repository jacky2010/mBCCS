package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ApDomainByType;
import java.util.List;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class GetListBusTypeIdRequireResponse extends DataResponse {

    @SerializedName("lstApDomain")
    @Expose
    private List<ApDomainByType> apDomainList;

    public List<ApDomainByType> getApDomainList() {
        return apDomainList;
    }

    public void setApDomainList(List<ApDomainByType> apDomainList) {
        this.apDomainList = apDomainList;
    }
}
