package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ApParamsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 7/15/17.
 */

public class GetListApParamsResponse {
    @SerializedName("listApParams")
    @Expose
    private List<ApParamsModel> mApParamsModelList;

    public List<ApParamsModel> getApParamsModelList() {
        return mApParamsModelList;
    }

    public void setApParamsModelList(List<ApParamsModel> mApParamsModelList) {
        this.mApParamsModelList = mApParamsModelList;
    }

    public List<String> getListApParams() {
        List<String> mList = new ArrayList<>();
        if (getApParamsModelList() != null) {
            for (ApParamsModel model : getApParamsModelList()) {
                mList.add(model.name);
            }
        }
        return mList;
    }
}
