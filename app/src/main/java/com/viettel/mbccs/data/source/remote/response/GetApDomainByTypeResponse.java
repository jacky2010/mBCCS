package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ApDomainByType;
import java.util.List;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class GetApDomainByTypeResponse {

    @SerializedName("listApDomain")
    @Expose
    private List<ApDomainByType> apDomainByTypeList;

    public List<ApDomainByType> getApDomainByTypeList() {
        return apDomainByTypeList;
    }

    public void setApDomainByTypeList(List<ApDomainByType> apDomainByTypeList) {
        this.apDomainByTypeList = apDomainByTypeList;
    }
}
