package com.viettel.mbccs.data.source.remote.request;

import com.viettel.mbccs.constance.TelServiceId;
import com.viettel.mbccs.constance.MobileType;

/**
 * Created by HuyQuyet on 6/22/17.
 */

public class GetListProductRequest  extends BaseRequest  {
    @MobileType
    private String subType;

    @TelServiceId
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

    public GetListProductRequest() {
        super();
    }
}
