package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by eo_cuong on 6/10/17.
 */
@IntDef({ SurveyType.SINGLE_CHOICE, SurveyType.MULTI_CHOICE, SurveyType.TEXT, SurveyType.NUMBER })
public @interface SurveyType {
    int SINGLE_CHOICE = 2;
    int MULTI_CHOICE = 1;
    int TEXT = 3;
    int NUMBER = 4;
}
