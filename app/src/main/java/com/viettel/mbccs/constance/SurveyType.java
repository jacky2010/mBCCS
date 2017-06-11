package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

/**
 * Created by eo_cuong on 6/10/17.
 */
@StringDef({ SurveyType.SINGLE_CHOICE, SurveyType.MULTI_CHOICE })
public @interface SurveyType {
    String SINGLE_CHOICE = "single";
    String MULTI_CHOICE = "multi";
}
