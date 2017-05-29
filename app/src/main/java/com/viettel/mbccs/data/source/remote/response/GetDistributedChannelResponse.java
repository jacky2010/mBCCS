package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.BranchItem;

/**
 * Created by minhnx on 5/29/17.
 */

public class GetDistributedChannelResponse extends DataResponse {
    @Expose
    @SerializedName("branch")
    private BranchItem branchItem;

    public BranchItem getBranchItem() {
        return branchItem;
    }

    public void setBranchItem(BranchItem branchItem) {
        this.branchItem = branchItem;
    }
}
