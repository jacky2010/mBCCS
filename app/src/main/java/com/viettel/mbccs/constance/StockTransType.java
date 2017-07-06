package com.viettel.mbccs.constance;

/**
 * Created by FRAMGIA\hoang.van.cuong on 06/07/2017.
 */

public @interface StockTransType {
    long TRANS_IMPORT = 2L; //Nhap kho
    long TRANS_EXPORT = 1L; //Xuat kho
    long TRANS_RECOVER = 3L; //Thu hoi hang
}
