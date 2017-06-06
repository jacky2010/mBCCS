package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 5/21/17.
 */

public class SearchBranchRequest {
    @SerializedName("objectBO")
    @Expose
    private SearchBranchKeyRequest objectBO;

    public SearchBranchKeyRequest getObjectBO() {
        return objectBO;
    }

    public void setObjectBO(SearchBranchKeyRequest objectBO) {
        this.objectBO = objectBO;
    }
}
