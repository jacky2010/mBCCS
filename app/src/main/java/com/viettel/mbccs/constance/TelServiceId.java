package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by HuyQuyet on 6/24/17.
 */

@IntDef({
        TelServiceId.Mobile, TelServiceId.Homephone, TelServiceId.ADSL, TelServiceId.Leasedline,
        TelServiceId.PSTN, TelServiceId.White_leaseline, TelServiceId.FTTH
})
public @interface TelServiceId {
    int Mobile = 1;
    int Homephone = 2;
    int ADSL = 4;
    int Leasedline = 5;
    int PSTN = 9;
    int White_leaseline = 12;
    int FTTH = 15;
}
