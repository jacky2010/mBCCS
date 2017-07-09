package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SerialBO;
import java.util.List;

/**
 * Created by eo_cuong on 7/8/17.
 */

public class GetStockTransSerialDetailResponse {

    @SerializedName("lstStockTransSerial")
    private List<SerialBO> mSerialBOList;

    public List<SerialBO> getSerialBOList() {
        return mSerialBOList;
    }

    public void setSerialBOList(List<SerialBO> serialBOList) {
        mSerialBOList = serialBOList;
    }
}
