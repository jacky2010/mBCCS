package com.viettel.mbccs.data.source.remote.request;

import java.io.Serializable;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class GetOrderInfoRequest implements Serializable{
    private long saleOrderId;

    public long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }
}
