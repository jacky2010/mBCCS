package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTransDetail;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public class ListStockTransDetailsReponse extends DataResponse {

    @SerializedName("lstStockTransDetail")
    @Expose
    List<StockTransDetail> mStockTransDetails;

    public List<StockTransDetail> getStockTransDetails() {
        return mStockTransDetails;
    }

    public void setStockTransDetails(List<StockTransDetail> stockTransDetails) {
        mStockTransDetails = stockTransDetails;
    }
}
