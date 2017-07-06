package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleTrans;

import java.util.List;

/**
 * Created by jacky on 7/3/17.
 */

public class GetListSearchTransResponse {

    @SerializedName("lstSaleTrans")
    @Expose
    public List<SaleTrans> mListSaleTrans;
}
