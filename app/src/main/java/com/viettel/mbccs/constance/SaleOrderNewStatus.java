package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 6/14/17.
 */

@StringDef({
        SaleOrderNewStatus.CHUA_LEN_GIAO_DICH, SaleOrderNewStatus.DA_LEN_GIAO_DICH,
        SaleOrderNewStatus.DA_HUY
})
public @interface SaleOrderNewStatus {
    String CHUA_LEN_GIAO_DICH = "1";
    String DA_LEN_GIAO_DICH = "2";
    String DA_HUY = "3";
}
