package com.viettel.mbccs.screen.sell.orders.listener;

import com.viettel.mbccs.constance.OrderStatus;

/**
 * Created by HuyQuyet on 7/1/17.
 */

public interface ChangeStatusOrderCallback {
    void callback(long saleOrdersId, @OrderStatus String orderStatus);
}
