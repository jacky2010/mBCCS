package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by eo_cuong on 5/22/17.
 */
@IntDef({ StockStateId.TYPE_FAIL, StockStateId.TYPE_NEW })
public @interface StockStateId {
    long TYPE_NEW = 1;
    long TYPE_OLD = 2;
    long TYPE_FAIL = 3;
}
