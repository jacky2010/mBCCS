package com.viettel.mbccs.screen.change.installation;

import android.support.annotation.IntDef;

@IntDef({TypeChangeAddress.CHANGE_ADDRESS_NEW, TypeChangeAddress.CHANGE_SURVEY, TypeChangeAddress.CHANGE_FACILITY})
public @interface TypeChangeAddress {
    int CHANGE_ADDRESS_NEW = 0;
    int CHANGE_SURVEY = 1;
    int CHANGE_FACILITY = 2;
}
