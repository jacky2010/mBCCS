package com.viettel.mbccs;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.activeandroid.ActiveAndroid;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class MBCCSApplication extends Application {

    private static Context mSelf;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        MultiDex.install(this);
        mSelf = this;
    }

    public static Context self() {
        return mSelf;
    }
}
