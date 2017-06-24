package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.LamModel;

import java.util.List;

/**
 * Created by jacky on 6/23/17.
 */

public class GetListDsLamByTeamIdResponse  extends DataResponse {

    @SerializedName("listdsLam")
    public List<LamModel> mListDSLam;
}
