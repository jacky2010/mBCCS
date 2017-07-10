package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by HuyQuyet on 7/8/17.
 */

@IntDef({ WarehouseType.KHO_CUA_HANG, WarehouseType.KHO_NHAN_VIEN })
public @interface WarehouseType {
    int KHO_NHAN_VIEN = 2;
    int KHO_CUA_HANG = 1;
}
