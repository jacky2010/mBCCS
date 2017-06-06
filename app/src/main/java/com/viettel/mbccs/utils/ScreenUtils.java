package com.viettel.mbccs.utils;

import android.content.Context;

/**
 * Created by jacky on 6/5/17.
 */

public class ScreenUtils {

    public static int px2dip(Context mContext, float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
