package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 6/14/17.
 */

@StringDef({ ReasonType.HUY_DON_HANG })
public @interface ReasonType {
    String HUY_DON_HANG = "REJECT_ORDER";
}
