package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.OwnerCode;
import java.util.List;

/**
 * Created by eo_cuong on 7/6/17.
 */

public class GetListOwneCodeReponse {

    @SerializedName("listStaff")
    @Expose
    private List<OwnerCode> mOwnerCodes;

    public List<OwnerCode> getOwnerCodes() {
        return mOwnerCodes;
    }

    public void setOwnerCodes(List<OwnerCode> ownerCodes) {
        mOwnerCodes = ownerCodes;
    }
}
