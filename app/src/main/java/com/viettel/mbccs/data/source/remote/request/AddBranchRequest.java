package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.BranchItem;

/**
 * Created by minhnx on 5/21/17.
 */

public class AddBranchRequest extends BaseRequest {
    @SerializedName("objectBO")
    @Expose
    private BranchItem objectBO;

    public BranchItem getObjectBO() {
        return objectBO;
    }

    public void setObjectBO(BranchItem objectBO) {
        this.objectBO = objectBO;
    }

    public AddBranchRequest() {
        super();
    }
}
