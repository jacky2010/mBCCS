package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 6/24/17.
 */

@StringDef({ MobileType.TRA_TRUOC, MobileType.TRA_SAU })
public @interface MobileType {
    // prepaid
    String TRA_TRUOC = "2";

    // postpaid
    String TRA_SAU = "1";
}
