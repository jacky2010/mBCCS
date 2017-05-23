package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by HuyQuyet on 5/16/17.
 */

@IntDef({ OrderStatus.APPROVALS, OrderStatus.PENDING, OrderStatus.REJECT })
public @interface OrderStatus {
    int APPROVALS = 2;
    int PENDING = 1;
    int REJECT = 3;
}
