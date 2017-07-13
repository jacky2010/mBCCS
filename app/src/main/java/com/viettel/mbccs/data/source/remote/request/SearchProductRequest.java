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
    private String language;
    @Expose
    private String product;

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
