package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StockTrans;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 06/07/2017.
 */

public class GetListExpCmdResponse {

    @SerializedName("lstStockTrans")
    List<StockTrans> mStockTranses;

    public List<StockTrans> getStockTranses() {
        return mStockTranses;
    }

    public void setStockTranses(List<StockTrans> stockTranses) {
        mStockTranses = stockTranses;
    }
}
