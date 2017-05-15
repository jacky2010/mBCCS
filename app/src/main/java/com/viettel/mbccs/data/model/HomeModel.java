package com.viettel.mbccs.data.model;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class HomeModel {

    private String mTitle;

    private String mSubTitle;

    private float mPercentage;

    private int mType;

    public HomeModel() {
    }

    public HomeModel(String title, String subTitle, float percentage, int type) {
        mTitle = title;
        mSubTitle = subTitle;
        mPercentage = percentage;
        mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
    }

    public float getPercentage() {
        return mPercentage;
    }

    public void setPercentage(float percentage) {
        this.mPercentage = percentage;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }
}
