package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.viettel.mbccs.data.model.SearchProduct;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetProductInfoResponse extends DataResponse {
    @Expose
    private SearchProduct product;

    public SearchProduct getProduct() {
        return product;
    }

    public void setProduct(SearchProduct product) {
        this.product = product;
    }
}
