package com.viettel.mbccs.data.source.local.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SharedPrefs {
    private SharedPreferences mSharedpreferences;
    private volatile static SharedPrefs instance;

    private SharedPrefs() {
        mSharedpreferences = MBCCSApplication.self()
                .getSharedPreferences(Constants.SharePref.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefs getInstance() {
        if (instance == null) {
            instance = new SharedPrefs();
        }
        return instance;
    }

    public void set(String key, String value) {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();    // write to immediately instead of apply()
    }

    public String get(String key, String defValue) {
        return mSharedpreferences.getString(key, defValue);
    }

    public void set(String key, int value) {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int get(String key, int defValue) {
        return mSharedpreferences.getInt(key, defValue);
    }

    public void set(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean get(String key, boolean defValue) {
        return mSharedpreferences.getBoolean(key, defValue);
    }

    public void set(String key, long value) {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long get(String key, long defValue) {
        return mSharedpreferences.getLong(key, defValue);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedpreferences;
    }

    public void clear() {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.clear();
        editor.commit();
    }
}
