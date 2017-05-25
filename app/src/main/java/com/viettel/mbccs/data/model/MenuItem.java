package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class MenuItem implements Parcelable {

    /**
     * Id for menu to handle event, add more if need
     * Do not use Id 0, it's a placeholder for mock menu
     */
    @IntDef({
            MenuId.MENU_PLACEHOLDER, MenuId.MENU_DASHBOARD, MenuId.MENU_HELP, MenuId.MENU_SETTING,
            MenuId.MENU_QLKH, MenuId.MENU_QLTC, MenuId.MENU_BH_KHO_TC, MenuId.MENU_BH_CSKH,
            MenuId.MENU_BAO_CAO, MenuId.MENU_QLDB, MenuId.MENU_GIAO_VIEC_PHAT_SINH,
            MenuId.MENU_GIAO_VIEC_CS_KPP, MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG,
            MenuId.MENU_XEM_KHO
    })
    public @interface MenuId {
        int MENU_PLACEHOLDER = 0;
        int MENU_DASHBOARD = 1;
        int MENU_HELP = 2;
        int MENU_SETTING = 3;

        int MENU_QLKH = 4;
        int MENU_QLTC = 5;
        int MENU_BH_KHO_TC = 6;
        int MENU_BH_CSKH = 7;
        int MENU_BAO_CAO = 8;
        int MENU_QLDB = 9;

        int MENU_GIAO_VIEC_PHAT_SINH = 10;
        int MENU_GIAO_VIEC_CS_KPP = 11;

        int MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG = 12;
        int MENU_XEM_KHO = 13;
    }

    @MenuId
    private int mId;

    private String mTitle;

    @DrawableRes
    private int mIcon;

    private int mColor;

    private float mSize;

    public MenuItem() {
    }

    public MenuItem(@MenuId int id, String title, int resId, int color, int size) {
        mId = id;
        mTitle = title;
        mIcon = resId;
        mColor = color;
        mSize = size;
    }

    protected MenuItem(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mIcon = in.readInt();
        mColor = in.readInt();
        mSize = in.readFloat();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @DrawableRes
    public int getIcon() {
        return mIcon;
    }

    public void setIcon(@DrawableRes int resId) {
        mIcon = resId;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public float getSize() {
        return mSize;
    }

    public void setSize(float size) {
        mSize = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeInt(mIcon);
        dest.writeInt(mColor);
        dest.writeFloat(mSize);
    }

    @MenuId
    public int getId() {
        return mId;
    }

    public void setId(@MenuId int id) {
        mId = id;
    }
}
