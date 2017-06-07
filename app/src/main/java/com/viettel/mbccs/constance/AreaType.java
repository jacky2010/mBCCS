package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

@IntDef({ AreaType.TAB_TH, AreaType.TAB_TTKD, AreaType.TAB_BTS, AreaType.TAB_CLH })
public @interface AreaType {
    int TAB_TH = 0;
    int TAB_TTKD = 1;
    int TAB_BTS = 2;
    int TAB_CLH = 3;
}
