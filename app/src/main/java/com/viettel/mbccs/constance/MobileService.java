package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by HuyQuyet on 6/24/17.
 */

@IntDef({
        MobileService.Mobile, MobileService.Homephone, MobileService.ADSL, MobileService.Leasedline,
        MobileService.PSTN, MobileService.White_leaseline, MobileService.FTTH
})
public @interface MobileService {
    int Mobile = 1;
    int Homephone = 2;
    int ADSL = 4;
    int Leasedline = 5;
    int PSTN = 9;
    int White_leaseline = 12;
    int FTTH = 15;
}
