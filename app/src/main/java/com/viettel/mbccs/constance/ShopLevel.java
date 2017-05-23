package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by HuyQuyet on 5/23/17.
 */

@IntDef({ ShopLevel.CONG_TY, ShopLevel.CN, ShopLevel.TTKD, ShopLevel.SHOW_ROM })
public @interface ShopLevel {
    long CONG_TY = 1;
    long CN = 2;
    long TTKD = 3;
    long SHOW_ROM = 4;
}
