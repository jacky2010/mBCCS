package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Shop;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class GetListTTKDResponse extends DataResponse {

    @Expose
    @SerializedName("shopList")
    private List<Shop> shopList;

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
