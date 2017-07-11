package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.CurrBillCycle;
import java.util.List;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class GetCurrBillCycleResponse {

    @SerializedName("lstCurrBill")
    @Expose
    private List<CurrBillCycle> billCycleList;

    public List<CurrBillCycle> getBillCycleList() {
        return billCycleList;
    }

    public void setBillCycleList(List<CurrBillCycle> billCycleList) {
        this.billCycleList = billCycleList;
    }
}
