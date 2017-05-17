package com.viettel.mbccs.data.model;

import android.support.annotation.DrawableRes;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class MenuItem {

    private String mTitle;

    @DrawableRes
    private int mResId;

    public MenuItem() {
    }

    public MenuItem(String title, int resId) {
        mTitle = title;
        mResId = resId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @DrawableRes
    public int getResId() {
        return mResId;
    }

    public void setResId(@DrawableRes int resId) {
        this.mResId = resId;
    }
}
