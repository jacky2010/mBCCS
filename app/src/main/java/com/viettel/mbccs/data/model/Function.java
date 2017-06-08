package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Function implements Parcelable {

    protected Function(Parcel in) {
        mId = in.readInt();
        mIcon = in.readInt();
        mColor = in.readInt();
        mSize = in.readFloat();
        functionCode = in.readString();
        functionName = in.readString();
    }

    public static final Creator<Function> CREATOR = new Creator<Function>() {
        @Override
        public Function createFromParcel(Parcel in) {
            return new Function(in);
        }

        @Override
        public Function[] newArray(int size) {
            return new Function[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mIcon);
        dest.writeInt(mColor);
        dest.writeFloat(mSize);
        dest.writeString(functionCode);
        dest.writeString(functionName);
    }

    /**
     * Id for menu to handle event, add more if need
     * Do not use Id 0, it's a placeholder for mock menu
     */
    @IntDef({
            MenuId.MENU_PLACEHOLDER, MenuId.MENU_DASHBOARD, MenuId.MENU_CONG_VIEC,
            MenuId.MENU_BAN_HANG, MenuId.MENU_KHACH_HANG, MenuId.MENU_QUAN_LY_BAN_HANG,
            MenuId.MENU_QUAN_LY_THONG_TIN_KH, MenuId.MENU_QUAN_LY_DIA_BAN,
            MenuId.MENU_QUAN_LY_THU_CUOC, MenuId.MENU_QUAN_LY_CONG_VIEC, MenuId.MENU_QUAN_LY_KHO,
            MenuId.MENU_QUAN_LY_BAO_HANH, MenuId.MENU_QUAN_LY_CSKH, MenuId.MENU_QUAN_LY_TAI_CHINH,
            MenuId.MENU_BAO_CAO, MenuId.MENU_HELP, MenuId.MENU_SETTING,

            MenuId.MENU_BAN_LE, MenuId.MENU_BAN_CHO_KENH,
            MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG, MenuId.MENU_BAN_DICH_VU_VAS,
            MenuId.MENU_LAP_HOA_DON, MenuId.MENU_DAU_NOI_DI_DONG, MenuId.MENU_DAU_NOI_CO_DINH,
            MenuId.MENU_BAN_ANYPAY, MenuId.MENU_NAP_CHUYEN_ANYPAY,

            MenuId.MENU_DANG_KY_THONG_TIN, MenuId.MENU_CAP_NHAT_THONG_TIN, MenuId.MENU_DOI_SIM,
            MenuId.MENU_THAY_DOI_DIA_CHI_LAP_DAT,

            MenuId.MENU_TAO_KENH_PHAN_PHOI, MenuId.MENU_QUAN_LY_DBHC_BTS_KENH,
            MenuId.MENU_QUAN_LY_KPI_KPP, MenuId.MENU_QUAN_LY_THONG_TIN_KPP,
            MenuId.MENU_QUAN_LY_VAN_BAN_CSTT,

            MenuId.MENU_XAC_MINH, MenuId.MENU_GACH_NO, MenuId.MENU_THU_CUOC_NONG,
            MenuId.MENU_QUAN_LY_TIEN_DO_THU_CUOC,

            MenuId.MENU_GIAO_VIEC_TO_DOI, MenuId.MENU_GIAO_VIEC_PHAT_SINH,
            MenuId.MENU_GIAO_VIEC_CS_KPP, MenuId.MENU_DONG_VIEC,

            MenuId.MENU_XEM_KHO, MenuId.MENU_NHAP_HOA_DON, MenuId.MENU_XUAT_KHO_CAP_DUOI,
            MenuId.MENU_NHAP_KHO_CAP_TREN, MenuId.MENU_TRA_HANG_CAP_TREN,
            MenuId.MENU_NHAP_KHO_CAP_DUOI, MenuId.MENU_XUAT_KHO_CHO_NHAN_VIEN,
            MenuId.MENU_NV_XAC_NHAN_HANG, MenuId.MENU_NHAN_VIEN_TRA_HANG_CAP_TREN,
            MenuId.MENU_NHAP_KHO_TU_NHAN_VIEN, MenuId.MENU_KENH_ORDER_HANG,

            MenuId.MENU_TRA_CUU, MenuId.MENU_TIEP_NHAN_BH, MenuId.MENU_CHUYEN_MUC_BH,
            MenuId.MENU_TRA_BH,

            MenuId.MENU_SURVEY_KPP, MenuId.MENU_HOTNEW_CS_KPP, MenuId.MENU_KPP_FEEDBACK,
            MenuId.MENU_TRA_CUU_SP,

            MenuId.MENU_TAO_GIAY_NOP_TIEN, MenuId.MENU_PHE_DUYET_GIAY_NOP_TIEN,
            MenuId.MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN, MenuId.MENU_KHAI_BAO_GIA_KENH_CHAN_RET,

            MenuId.MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO, MenuId.MENU_BAO_CAO_CHAM_SOC_KENH,
            MenuId.MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH, MenuId.MENU_BAO_CAO_TON_KHO,
            MenuId.MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG
    })

    public @interface MenuId {
        int MENU_PLACEHOLDER = 0;
        int MENU_DASHBOARD = 1;
        int MENU_CONG_VIEC = 2;
        int MENU_BAN_HANG = 3;
        int MENU_KHACH_HANG = 4;
        int MENU_QUAN_LY_BAN_HANG = 5;
        int MENU_QUAN_LY_THONG_TIN_KH = 6;
        int MENU_QUAN_LY_DIA_BAN = 7;
        int MENU_QUAN_LY_THU_CUOC = 8;
        int MENU_QUAN_LY_CONG_VIEC = 9;
        int MENU_QUAN_LY_KHO = 10;
        int MENU_QUAN_LY_BAO_HANH = 11;
        int MENU_QUAN_LY_CSKH = 12;
        int MENU_QUAN_LY_TAI_CHINH = 13;
        int MENU_BAO_CAO = 14;
        int MENU_HELP = 15;
        int MENU_SETTING = 16;

        int MENU_BAN_LE = 501;
        int MENU_BAN_CHO_KENH = 502;
        int MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG = 503;
        int MENU_BAN_DICH_VU_VAS = 504;
        int MENU_LAP_HOA_DON = 505;
        int MENU_DAU_NOI_DI_DONG = 506;
        int MENU_DAU_NOI_CO_DINH = 507;
        int MENU_BAN_ANYPAY = 508;
        int MENU_NAP_CHUYEN_ANYPAY = 509;

        int MENU_DANG_KY_THONG_TIN = 601;
        int MENU_CAP_NHAT_THONG_TIN = 602;
        int MENU_DOI_SIM = 603;
        int MENU_THAY_DOI_DIA_CHI_LAP_DAT = 604;

        int MENU_TAO_KENH_PHAN_PHOI = 701;
        int MENU_QUAN_LY_DBHC_BTS_KENH = 702;
        int MENU_QUAN_LY_KPI_KPP = 703;
        int MENU_QUAN_LY_THONG_TIN_KPP = 704;
        int MENU_QUAN_LY_VAN_BAN_CSTT = 705;

        int MENU_XAC_MINH = 801;
        int MENU_GACH_NO = 802;
        int MENU_THU_CUOC_NONG = 803;
        int MENU_QUAN_LY_TIEN_DO_THU_CUOC = 804;

        int MENU_GIAO_VIEC_TO_DOI = 901;
        int MENU_GIAO_VIEC_PHAT_SINH = 902;
        int MENU_GIAO_VIEC_CS_KPP = 903;
        int MENU_DONG_VIEC = 904;

        int MENU_XEM_KHO = 1001;
        int MENU_NHAP_HOA_DON = 1002;
        int MENU_XUAT_KHO_CAP_DUOI = 1003;
        int MENU_NHAP_KHO_CAP_TREN = 1004;
        int MENU_TRA_HANG_CAP_TREN = 1005;
        int MENU_NHAP_KHO_CAP_DUOI = 1006;
        int MENU_XUAT_KHO_CHO_NHAN_VIEN = 1007;
        int MENU_NV_XAC_NHAN_HANG = 1008;
        int MENU_NHAN_VIEN_TRA_HANG_CAP_TREN = 1009;
        int MENU_NHAP_KHO_TU_NHAN_VIEN = 1010;
        int MENU_KENH_ORDER_HANG = 1011;

        int MENU_TRA_CUU = 1101;
        int MENU_TIEP_NHAN_BH = 1102;
        int MENU_CHUYEN_MUC_BH = 1103;
        int MENU_TRA_BH = 1104;

        int MENU_SURVEY_KPP = 1201;
        int MENU_HOTNEW_CS_KPP = 1202;
        int MENU_KPP_FEEDBACK = 1203;
        int MENU_TRA_CUU_SP = 1204;

        int MENU_TAO_GIAY_NOP_TIEN = 1301;
        int MENU_PHE_DUYET_GIAY_NOP_TIEN = 1302;
        int MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN = 1303;
        int MENU_KHAI_BAO_GIA_KENH_CHAN_RET = 1304;

        int MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO = 1401;
        int MENU_BAO_CAO_CHAM_SOC_KENH = 1402;
        int MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH = 1403;
        int MENU_BAO_CAO_TON_KHO = 1404;
        int MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG = 1405;
    }

    @MenuId
    private int mId;

    @DrawableRes
    private int mIcon;

    private int mColor;

    private float mSize;

    @SerializedName("functionCode")
    @Expose
    private String functionCode;
    @SerializedName("functionName")
    @Expose
    private String functionName;

    public Function(@MenuId int id, String functionName, /*String functionCode,*/
            @DrawableRes int icon) {
        mId = id;
        mIcon = icon;
        this.functionCode = functionCode;
        this.functionName = functionName;
    }

    @MenuId
    public int getId() {
        return mId;
    }

    public void setId(@MenuId int id) {
        mId = id;
    }

    @DrawableRes
    public int getIcon() {
        return mIcon;
    }

    public void setIcon(@DrawableRes int icon) {
        mIcon = icon;
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

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
