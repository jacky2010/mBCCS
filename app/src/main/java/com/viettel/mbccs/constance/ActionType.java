package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by FRAMGIA\hoang.van.cuong on 06/07/2017.
 */

@IntDef({ ActionType.ACTION_CANCEL, ActionType.ACTION_OTHER })
public @interface ActionType {
    int ACTION_CANCEL = 0;
    int ACTION_OTHER = 1;
}
