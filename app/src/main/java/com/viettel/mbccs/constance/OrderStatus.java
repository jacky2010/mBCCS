package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 5/16/17.
 */

@StringDef({ OrderStatus.APPROVALS, OrderStatus.PENDING, OrderStatus.REJECT })
public @interface OrderStatus {
    String APPROVALS = "2";
    String PENDING = "1";
    String REJECT = "3";
}
