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
            MenuId.MENU_PLACEHOLDER, MenuId.MENU_DASHBOARD, MenuId.MENU_CONG_VIEC,
            MenuId.MENU_BAN_HANG, MenuId.MENU_KHACH_HANG, MenuId.MENU_HELP, MenuId.MENU_SETTING,
            MenuId.MENU_DAU_NOI_QUAN_LY_KHACH_HANG, MenuId.MENU_QUAN_LY_THU_CUOC,
            MenuId.MENU_BAN_HANG_KHO_TAI_CHINH, MenuId.MENU_BH_CHAM_SOC_KHACH_HANG,
            MenuId.MENU_BAO_CAO, MenuId.MENU_QUAN_LY_DIA_BAN, MenuId.MENU_GIAO_VIEC_PHAT_SINH,
            MenuId.MENU_GIAO_VIEC_CS_KPP, MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG,
            MenuId.MENU_XEM_KHO, MenuId.MENU_SALE_RETAIL, MenuId.MENU_SALE_CHANNEL,
            MenuId.MENU_CHANNEL_ORDER, MenuId.MENU_NHAP_HOA_DON, MenuId.MENU_TD_LAP_DAT,
            MenuId.MENU_LAP_HOA_DON, MenuId.MENU_DANG_KY_THONG_TIN, MenuId.MENU_CAP_NHAT_THONG_TIN,
            MenuId.MENU_NHAP_KHO_CAP_DUOI, MenuId.MENU_CREATE_BRANCH, MenuId.MENU_CHANGE_SIM, MenuId.MENU_SELL_ANYPAY, MenuId.MENU_TRANSFER_ANYPAY, MenuId.MENU_DAU_NOI_CO_DINH,
            MenuId.MENU_SURVEY_KPP
    })
    public @interface MenuId {
        int MENU_PLACEHOLDER = 0;
        int MENU_DASHBOARD = 1;
        int MENU_CONG_VIEC = 2;
        int MENU_BAN_HANG = 3;
        int MENU_KHACH_HANG = 4;
        int MENU_DAU_NOI_QUAN_LY_KHACH_HANG = 5;
        int MENU_QUAN_LY_THU_CUOC = 6;
        int MENU_BAN_HANG_KHO_TAI_CHINH = 7;
        int MENU_BH_CHAM_SOC_KHACH_HANG = 8;
        int MENU_BAO_CAO = 9;
        int MENU_QUAN_LY_DIA_BAN = 10;
        int MENU_HELP = 11;
        int MENU_SETTING = 12;

        int MENU_DAU_NOI_DI_DONG = 501;
        int MENU_DAU_NOI_CO_DINH = 502;
        int MENU_DANG_KY_THONG_TIN = 503;
        int MENU_CAP_NHAT_THONG_TIN = 504;
        int MENU_TD_LAP_DAT = 506;
        int MENU_GIAO_VIEC_PHAT_SINH = 508;
        int MENU_GIAO_VIEC_CS_KPP = 509;

        int MENU_SALE_RETAIL = 701;
        int MENU_SALE_CHANNEL = 702;
        int MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG = 703;
        int MENU_BAN_DICH_VU_VAS = 704;
        int MENU_LAP_HOA_DON = 705;
        int MENU_BAN_ANYPAY = 706;
        int MENU_NAP_CHUYEN_ANYPAY = 707;
        int MENU_XEM_KHO = 708;
        int MENU_NHAP_HOA_DON = 709;
        int MENU_XUAT_KHO_CAP_DUOI = 710;
        int MENU_NHAP_KHO_CAP_TREN = 711;
        int MENU_TRA_HANG_CAP_TREN = 712;
        int MENU_NHAP_KHO_CAP_DUOI = 713;
        int MENU_XUAT_KHO_CHO_NHAN_VIEN = 714;
        int MENU_NV_XAC_NHAN_HANG = 715;
        int MENU_NHAN_VIEN_TRA_HANG_CAP_TREN = 716;
        int MENU_NHAP_KHO_TU_NHAN_VIEN = 717;
        int MENU_CHANNEL_ORDER = 718;
        int MENU_TAO_GIAY_NOP_TIEN = 719;
        int MENU_PHE_DYUET_GIAY_NOP_TIEN = 720;
        int MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN = 721;

        int MENU_CREATE_BRANCH = 25;
        int MENU_CHANGE_SIM = 26;
        int MENU_SELL_ANYPAY = 27;
        int MENU_TRANSFER_ANYPAY = 28;
        int MENU_SURVEY_KPP = 29;
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
