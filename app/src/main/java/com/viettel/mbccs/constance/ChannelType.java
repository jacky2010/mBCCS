package com.viettel.mbccs.constance;
import android.support.annotation.StringDef;

@StringDef({ ChannelType.SHOP, ChannelType.STAFF })
public @interface ChannelType {
    String SHOP = "1";
    String STAFF = "2";
}
