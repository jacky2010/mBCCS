package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.ModelSale;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetTotalStockResponse extends DataResponse {

    @Expose
    @SerializedName("lstModelSale")
    private List<ModelSale> modelSaleList;

    public List<ModelSale> getModelSaleList() {
        return modelSaleList;
    }

    public void setModelSaleList(List<ModelSale> modelSaleList) {
        this.modelSaleList = modelSaleList;
    }
}
