package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 5/16/17.
 */

@StringDef({ ChannelType.SHOP, ChannelType.STAFF })
public @interface ChannelType {
    String SHOP = "1";
    String STAFF = "2";
}
