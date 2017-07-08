package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by HuyQuyet on 7/7/17.
 */
@StringDef({ Gender.MALE, Gender.FEMALE })
public @interface Gender {
    String MALE = "M";
    String FEMALE = "F";
}
