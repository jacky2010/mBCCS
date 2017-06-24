package com.viettel.mbccs.data.source.remote.request;

import com.viettel.mbccs.constance.MobileService;
import com.viettel.mbccs.constance.MobileType;

/**
 * Created by HuyQuyet on 6/22/17.
 */

public class GetListProductRequest {
    @MobileType
    private String subType;

    @MobileService
    private int telServiceId;

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
}
