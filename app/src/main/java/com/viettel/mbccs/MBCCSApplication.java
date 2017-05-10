package com.viettel.mbccs;

import android.app.Application;
import android.content.Context;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class MBCCSApplication extends Application {

    private static Context mSelf;

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
    }

    public static Context self() {
        return mSelf;
    }
}
