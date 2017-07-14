package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;

/**
 * Created by minhnx on 6/7/17.
 */

public class SearchProductRequest extends BaseRequest {

    public SearchProductRequest() {
        super();
    }

    @Expose
    private String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
