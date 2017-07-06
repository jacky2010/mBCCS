package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.R;

public class Function implements Parcelable {

    protected Function(Parcel in) {
        mIcon = in.readInt();
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
        dest.writeInt(mIcon);
        dest.writeString(functionCode);
        dest.writeString(functionName);
    }

    @StringDef({
            MenuId.MENU_BAN_LE, MenuId.MENU_BAN_CHO_KENH,
            MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG, MenuId.MENU_BAN_DICH_VU_VAS,
            MenuId.MENU_LAP_HOA_DON, MenuId.MENU_DAU_NOI_DI_DONG, MenuId.MENU_DAU_NOI_CO_DINH,
            MenuId.MENU_BAN_ANYPAY, MenuId.MENU_NAP_CHUYEN_ANYPAY,

            MenuId.MENU_DANG_KY_THONG_TIN, MenuId.MENU_CAP_NHAT_THONG_TIN, MenuId.MENU_DOI_SIM,
            MenuId.MENU_THAY_DOI_DIA_CHI_LAP_DAT,
            //            MenuId.MENU_UPLOAD_ANH,

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
            MenuId.MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH, MenuId.MENU_BAO_CAO_HOA_HONG,
            MenuId.MENU_BAO_CAO_TON_KHO, MenuId.MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG
    })

    public @interface MenuId {
        String MENU_BAN_LE = "BANLE";
        String MENU_BAN_CHO_KENH = "BANKE";
        String MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG = "BANDI";
        String MENU_BAN_DICH_VU_VAS = "BANVA";
        String MENU_LAP_HOA_DON = "LAPHD";
        String MENU_DAU_NOI_DI_DONG = "DNDD";
        String MENU_DAU_NOI_CO_DINH = "DNCD";
        String MENU_BAN_ANYPAY = "BANAN";
        String MENU_NAP_CHUYEN_ANYPAY = "NAPAN";

        String MENU_DANG_KY_THONG_TIN = "DANGK";
        String MENU_CAP_NHAT_THONG_TIN = "CAPNH";
        String MENU_DOI_SIM = "DOISI";
        String MENU_THAY_DOI_DIA_CHI_LAP_DAT = "DOIDC";

        String MENU_TAO_KENH_PHAN_PHOI = "TAOKE";
        String MENU_QUAN_LY_DBHC_BTS_KENH = "QLDBA";
        String MENU_QUAN_LY_KPI_KPP = "QLKPI";
        String MENU_QUAN_LY_THONG_TIN_KPP = "QLKPP";
        String MENU_QUAN_LY_VAN_BAN_CSTT = "QLVBC";

        String MENU_XAC_MINH = "XACMI";
        String MENU_GACH_NO = "GACHN";
        String MENU_THU_CUOC_NONG = "THUCU";
        String MENU_QUAN_LY_TIEN_DO_THU_CUOC = "QLTHU";

        String MENU_DONG_VIEC = "DONGV";
        String MENU_GIAO_VIEC_PHAT_SINH = "GIAOPS";
        String MENU_GIAO_VIEC_CS_KPP = "GIAOKPP";
        String MENU_GIAO_VIEC_TO_DOI = "MENU_GIAO_VIEC_TO_DOI";

        String MENU_XEM_KHO = "XEMKH";
        String MENU_NHAP_HOA_DON = "NHAPHD";
        String MENU_XUAT_KHO_CAP_DUOI = "XUATCD";
        String MENU_NHAP_KHO_CAP_TREN = "NHAPCT";
        String MENU_TRA_HANG_CAP_TREN = "TRACT";
        String MENU_NHAP_KHO_CAP_DUOI = "NHAPCD";
        String MENU_XUAT_KHO_CHO_NHAN_VIEN = "XUATNV";
        String MENU_NV_XAC_NHAN_HANG = "NVNHA";
        String MENU_NHAN_VIEN_TRA_HANG_CAP_TREN = "NVTRA";
        String MENU_NHAP_KHO_TU_NHAN_VIEN = "NHAPN";
        String MENU_KENH_ORDER_HANG = "KENHO";

        String MENU_TRA_CUU = "TNBAH";
        String MENU_TIEP_NHAN_BH = "MENU_TIEP_NHAN_BH";
        String MENU_CHUYEN_MUC_BH = "CMBAH";
        String MENU_TRA_BH = "TRABH";

        String MENU_SURVEY_KPP = "SURVE";
        String MENU_HOTNEW_CS_KPP = "HOTNE";
        String MENU_KPP_FEEDBACK = "KPPFB";
        String MENU_TRA_CUU_SP = "TRASP";

        String MENU_TAO_GIAY_NOP_TIEN = "TAONT";
        String MENU_PHE_DUYET_GIAY_NOP_TIEN = "PHENT";
        String MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN = "DOIST";
        String MENU_KHAI_BAO_GIA_KENH_CHAN_RET = "KBGKR";

        String MENU_BAO_CAO_CHAM_SOC_KENH = "BCCSK";
        String MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH = "BCTSCSK";
        String MENU_BAO_CAO_HOA_HONG = "BCHOAH";
        String MENU_BAO_CAO_TON_KHO = "BCTONK";
        String MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG = "BCGCTB";
        String MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO = "BCDSK";
    }

    @StringDef({
            TopMenu.MENU_QUAN_LY_BAN_HANG, TopMenu.MENU_QUAN_LY_THONG_TIN_KH,
            TopMenu.MENU_QUAN_LY_DIA_BAN, TopMenu.MENU_QUAN_LY_THU_CUOC,
            TopMenu.MENU_QUAN_LY_CONG_VIEC, TopMenu.MENU_QUAN_LY_KHO, TopMenu.MENU_QUAN_LY_BAO_HANH,
            TopMenu.MENU_QUAN_LY_CSKH, TopMenu.MENU_QUAN_LY_TAI_CHINH, TopMenu.MENU_BAO_CAO
    })

    public @interface TopMenu {
        String MENU_QUAN_LY_BAN_HANG = "M_SALES";
        String MENU_QUAN_LY_THONG_TIN_KH = "M_CUST";
        String MENU_QUAN_LY_DIA_BAN = "M_AREA";
        String MENU_QUAN_LY_THU_CUOC = "M_PAYMENT";
        String MENU_QUAN_LY_CONG_VIEC = "M_TASK";
        String MENU_QUAN_LY_KHO = "M_STOCK";
        String MENU_QUAN_LY_BAO_HANH = "M_WARR";
        String MENU_QUAN_LY_CSKH = "M_CUSTCARE";
        String MENU_QUAN_LY_TAI_CHINH = "M_FINANCE";
        String MENU_BAO_CAO = "M_REPORT";

        String MENU_DASHBOARD = "M_DASHBOARD";
        String MENU_HELP = "M_HELP";
        String MENU_SETTINGS = "M_SETTINGS";
        String MENU_MORE = "M_MORE";
    }

    @DrawableRes
    private int mIcon;

    @SerializedName("functionCode")
    @Expose
    private String functionCode;
    @SerializedName("functionName")
    @Expose
    private String functionName;

    public Function(String functionCode, String functionName, @DrawableRes int icon) {
        mIcon = icon;
        this.functionCode = functionCode;
        this.functionName = functionName;
    }

    @DrawableRes
    public int getIcon() {
        if (mIcon != 0) {
            return mIcon;
        }

        switch (functionCode) {
            case Function.MenuId.MENU_BAN_LE:
                return R.drawable.ic_retail;
            case Function.MenuId.MENU_BAN_CHO_KENH:
                return R.drawable.ic_sales_tran;
            case Function.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG:
                return R.drawable.ic_sales_channel;
            case Function.MenuId.MENU_BAN_DICH_VU_VAS:
                return R.drawable.ic_sales_vas;
            case Function.MenuId.MENU_LAP_HOA_DON:
                return R.drawable.ic_make_invoice;
            case Function.MenuId.MENU_DAU_NOI_DI_DONG:
                return R.drawable.ic_registermb_sub;
            case Function.MenuId.MENU_DAU_NOI_CO_DINH:
                return R.drawable.ic_register_fixed_broad_band;
            case Function.MenuId.MENU_BAN_ANYPAY:
                return R.drawable.ic_sales_anypay;
            case Function.MenuId.MENU_NAP_CHUYEN_ANYPAY:
                return R.drawable.ic_chuyen_anypay;

            case Function.MenuId.MENU_DANG_KY_THONG_TIN:
            case Function.MenuId.MENU_CAP_NHAT_THONG_TIN:
                break;
            case Function.MenuId.MENU_DOI_SIM:
                return R.drawable.ic_doi_sim;
            case Function.MenuId.MENU_THAY_DOI_DIA_CHI_LAP_DAT:
                return R.drawable.ic_add_black_24dp;

            case Function.MenuId.MENU_TAO_KENH_PHAN_PHOI:
            case Function.MenuId.MENU_QUAN_LY_DBHC_BTS_KENH:
            case Function.MenuId.MENU_QUAN_LY_KPI_KPP:
            case Function.MenuId.MENU_QUAN_LY_THONG_TIN_KPP:
            case Function.MenuId.MENU_QUAN_LY_VAN_BAN_CSTT:
                return R.drawable.ic_add_black_24dp;

            case Function.MenuId.MENU_XAC_MINH:
            case Function.MenuId.MENU_GACH_NO:
            case Function.MenuId.MENU_THU_CUOC_NONG:
                break;
            case Function.MenuId.MENU_QUAN_LY_TIEN_DO_THU_CUOC:
                return R.drawable.ic_finance;

            case Function.MenuId.MENU_GIAO_VIEC_TO_DOI:
                return R.drawable.ic_assign_group;
            case Function.MenuId.MENU_GIAO_VIEC_PHAT_SINH:
                return R.drawable.ic_assign_more;
            case Function.MenuId.MENU_GIAO_VIEC_CS_KPP:
                return R.drawable.ic_care_kpp;
            case Function.MenuId.MENU_DONG_VIEC:
                return R.drawable.ic_close_task;

            case Function.MenuId.MENU_XEM_KHO:
                return R.drawable.ic_stock_seestock;
            case Function.MenuId.MENU_NHAP_HOA_DON:
                return R.drawable.ic_stock_importbill;
            case Function.MenuId.MENU_XUAT_KHO_CAP_DUOI:
                return R.drawable.ic_export_stock_under;
            case Function.MenuId.MENU_NHAP_KHO_CAP_TREN:
                return R.drawable.ic_import_stockup;
            case Function.MenuId.MENU_TRA_HANG_CAP_TREN:
                return R.drawable.ic_refund_up;
            case Function.MenuId.MENU_NHAP_KHO_CAP_DUOI:
                return R.drawable.ic_stock_importstockunder;
            case Function.MenuId.MENU_XUAT_KHO_CHO_NHAN_VIEN:
                return R.drawable.ic_stock_exportstaff;
            case Function.MenuId.MENU_NV_XAC_NHAN_HANG:
                return R.drawable.ic_stock_staffconfirm;
            case Function.MenuId.MENU_NHAN_VIEN_TRA_HANG_CAP_TREN:
                return R.drawable.ic_stock_staffrefundgoods;
            case Function.MenuId.MENU_NHAP_KHO_TU_NHAN_VIEN:
                return R.drawable.ic_stock_importtostaff;
            case Function.MenuId.MENU_KENH_ORDER_HANG:
                return R.drawable.ic_stock_kpp;

            case Function.MenuId.MENU_TRA_CUU:
                return R.drawable.ic_tra_cuu_bh;
            case Function.MenuId.MENU_TIEP_NHAN_BH:
                return R.drawable.ic_accept_bh;
            case Function.MenuId.MENU_CHUYEN_MUC_BH:
            case Function.MenuId.MENU_TRA_BH:
                return R.drawable.ic_add_black_24dp;

            case Function.MenuId.MENU_TRA_CUU_SP:
                return R.drawable.ic_cskh_search;
            case Function.MenuId.MENU_SURVEY_KPP:
                return R.drawable.ic_cskh_survey;
            case Function.MenuId.MENU_HOTNEW_CS_KPP:
                return R.drawable.ic_cskh_hotnew;
            case Function.MenuId.MENU_KPP_FEEDBACK:
                return R.drawable.ic_cskh_feedback;

            case Function.MenuId.MENU_TAO_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_PHE_DUYET_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_KHAI_BAO_GIA_KENH_CHAN_RET:
                return R.drawable.ic_add_black_24dp;

            case Function.MenuId.MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO:
            case Function.MenuId.MENU_BAO_CAO_CHAM_SOC_KENH:
            case Function.MenuId.MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH:
            case Function.MenuId.MENU_BAO_CAO_TON_KHO:
            case Function.MenuId.MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG:
                return R.drawable.ic_add_black_24dp;

            case TopMenu.MENU_QUAN_LY_BAN_HANG:
                return R.drawable.ic_shoping_cart;
            case TopMenu.MENU_QUAN_LY_THONG_TIN_KH:
                return R.drawable.ic_customer;
            case TopMenu.MENU_QUAN_LY_DIA_BAN:
                return R.drawable.ic_area;
            case TopMenu.MENU_QUAN_LY_THU_CUOC:
                return R.drawable.ic_billing;
            case TopMenu.MENU_QUAN_LY_CONG_VIEC:
                return R.drawable.ic_work;
            case TopMenu.MENU_QUAN_LY_KHO:
                return R.drawable.ic_stock;
            case TopMenu.MENU_QUAN_LY_BAO_HANH:
                return R.drawable.ic_bao_hanh;
            case TopMenu.MENU_QUAN_LY_CSKH:
                return R.drawable.ic_call_center;
            case TopMenu.MENU_QUAN_LY_TAI_CHINH:
                return R.drawable.ic_finance;
            case TopMenu.MENU_BAO_CAO:
                return R.drawable.ic_report;
        }
        return R.drawable.ic_add_black_24dp;
    }

    public void setIcon(@DrawableRes int icon) {
        mIcon = icon;
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

    public String getParentCode() {
        switch (functionCode) {
            case Function.MenuId.MENU_BAN_LE:
            case Function.MenuId.MENU_BAN_CHO_KENH:
            case Function.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG:
            case Function.MenuId.MENU_BAN_DICH_VU_VAS:
            case Function.MenuId.MENU_LAP_HOA_DON:
            case Function.MenuId.MENU_DAU_NOI_DI_DONG:
            case Function.MenuId.MENU_DAU_NOI_CO_DINH:
            case Function.MenuId.MENU_BAN_ANYPAY:
            case Function.MenuId.MENU_NAP_CHUYEN_ANYPAY:
                return TopMenu.MENU_QUAN_LY_BAN_HANG;

            case Function.MenuId.MENU_DANG_KY_THONG_TIN:
            case Function.MenuId.MENU_CAP_NHAT_THONG_TIN:
            case Function.MenuId.MENU_DOI_SIM:
            case Function.MenuId.MENU_THAY_DOI_DIA_CHI_LAP_DAT:
                return TopMenu.MENU_QUAN_LY_THONG_TIN_KH;

            case Function.MenuId.MENU_TAO_KENH_PHAN_PHOI:
            case Function.MenuId.MENU_QUAN_LY_DBHC_BTS_KENH:
            case Function.MenuId.MENU_QUAN_LY_KPI_KPP:
            case Function.MenuId.MENU_QUAN_LY_THONG_TIN_KPP:
            case Function.MenuId.MENU_QUAN_LY_VAN_BAN_CSTT:
                return TopMenu.MENU_QUAN_LY_DIA_BAN;

            case Function.MenuId.MENU_XAC_MINH:
            case Function.MenuId.MENU_GACH_NO:
            case Function.MenuId.MENU_THU_CUOC_NONG:
            case Function.MenuId.MENU_QUAN_LY_TIEN_DO_THU_CUOC:
                return TopMenu.MENU_QUAN_LY_THU_CUOC;

            case Function.MenuId.MENU_GIAO_VIEC_TO_DOI:
            case Function.MenuId.MENU_GIAO_VIEC_PHAT_SINH:
            case Function.MenuId.MENU_GIAO_VIEC_CS_KPP:
            case Function.MenuId.MENU_DONG_VIEC:
                return TopMenu.MENU_QUAN_LY_CONG_VIEC;

            case Function.MenuId.MENU_XEM_KHO:
            case Function.MenuId.MENU_NHAP_HOA_DON:
            case Function.MenuId.MENU_XUAT_KHO_CAP_DUOI:
            case Function.MenuId.MENU_NHAP_KHO_CAP_TREN:
            case Function.MenuId.MENU_TRA_HANG_CAP_TREN:
            case Function.MenuId.MENU_NHAP_KHO_CAP_DUOI:
            case Function.MenuId.MENU_XUAT_KHO_CHO_NHAN_VIEN:
            case Function.MenuId.MENU_NV_XAC_NHAN_HANG:
            case Function.MenuId.MENU_NHAN_VIEN_TRA_HANG_CAP_TREN:
            case Function.MenuId.MENU_NHAP_KHO_TU_NHAN_VIEN:
            case Function.MenuId.MENU_KENH_ORDER_HANG:
                return TopMenu.MENU_QUAN_LY_KHO;

            case Function.MenuId.MENU_TRA_CUU:
            case Function.MenuId.MENU_TIEP_NHAN_BH:
            case Function.MenuId.MENU_CHUYEN_MUC_BH:
            case Function.MenuId.MENU_TRA_BH:
                return TopMenu.MENU_QUAN_LY_BAO_HANH;

            case Function.MenuId.MENU_TRA_CUU_SP:
            case Function.MenuId.MENU_SURVEY_KPP:
            case Function.MenuId.MENU_HOTNEW_CS_KPP:
            case Function.MenuId.MENU_KPP_FEEDBACK:
                return TopMenu.MENU_QUAN_LY_CSKH;

            case Function.MenuId.MENU_TAO_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_PHE_DUYET_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN:
            case Function.MenuId.MENU_KHAI_BAO_GIA_KENH_CHAN_RET:
                return TopMenu.MENU_QUAN_LY_TAI_CHINH;

            case Function.MenuId.MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO:
            case Function.MenuId.MENU_BAO_CAO_CHAM_SOC_KENH:
            case Function.MenuId.MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH:
            case Function.MenuId.MENU_BAO_CAO_TON_KHO:
            case Function.MenuId.MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG:
                return TopMenu.MENU_BAO_CAO;
        }

        return "";
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Function function = (Function) obj;
            return function.getFunctionCode().equals(getFunctionCode());
        } catch (Exception e) {
            return false;
        }
    }
}
