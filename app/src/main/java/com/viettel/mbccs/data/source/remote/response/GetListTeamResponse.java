package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Shop;

import java.util.List;

/**
 * Created by jacky on 6/23/17.
 */

public class GetListTeamResponse extends DataResponse {

    @SerializedName("listShopSM")
    public List<Shop> mListShopSM;
}
