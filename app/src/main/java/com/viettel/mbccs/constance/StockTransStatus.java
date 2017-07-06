package com.viettel.mbccs.constance;

import android.support.annotation.IntDef;

/**
 * Created by eo_cuong on 7/5/17.
 */
public @interface StockTransStatus {
    //Trang thai giao dich kho
    long TRANS_NON_NOTE = 1; //Chua lap phieu
    long TRANS_NOTED = 2; //Da lap phieu
    long TRANS_DONE = 3; //Da xuat (nhap)
    long TRANS_EXP_IMPED = 4; //phieu xuat da lap lenh (phieu) nhap
    long TRANS_CANCEL = 5; //Da huy giao dich
    long TRANS_REJECT = 6; //giao dich xuat kho bi tu choi nhap
    long TRANS_CTV_WAIT = 3; //Lap phieu cho xac nhan cua CTV
    long TRANS_CTV_DONE = 4; //CTV da xac nhan va giao dich thanh cong
    long TRANS_CTV_CANCLE = 6; //CTV tu choi giao dich
}
