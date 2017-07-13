package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.viettel.mbccs.data.model.SearchProduct;

import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class SearchProductResponse extends DataResponse {
    @Expose
    private List<SearchProduct> listProducts;

    public List<SearchProduct> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<SearchProduct> listProducts) {
        this.listProducts = listProducts;
    }
}
