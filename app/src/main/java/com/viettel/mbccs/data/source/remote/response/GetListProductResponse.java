package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Product;
import java.util.List;

/**
 * Created by HuyQuyet on 6/22/17.
 */

public class GetListProductResponse {

    @Expose
    @SerializedName("listProduct")
    private List<Product> listProduct;

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
