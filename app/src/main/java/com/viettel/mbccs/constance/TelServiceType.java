package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 6/24/17.
 */

@StringDef({
        TelServiceType.Mobile, TelServiceType.Homephone, TelServiceType.ADSL,
        TelServiceType.Leasedline, TelServiceType.PSTN, TelServiceType.White_leaseline,
        TelServiceType.FTTH
})
public @interface TelServiceType {
    String Mobile = "M";
    String Homephone = "H";
    String ADSL = "A";
    String Leasedline = "L";
    String PSTN = "P";
    String White_leaseline = "W";
    String FTTH = "F";
}
