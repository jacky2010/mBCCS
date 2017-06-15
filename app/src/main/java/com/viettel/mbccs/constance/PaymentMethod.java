package com.viettel.mbccs.constance;

import android.support.annotation.StringDef;

@StringDef({
        PaymentMethod.PAYMENT_CASH, PaymentMethod.PAYMENT_VIA_BANCO,
        PaymentMethod.PAYMENT_BANK_PLUS, PaymentMethod.PAYMENT_POR_CHEQUE,
        PaymentMethod.PAYMENT_WELLET
})
public @interface PaymentMethod {
    String PAYMENT_CASH = "1";
    String PAYMENT_VIA_BANCO = "2";
    String PAYMENT_BANK_PLUS = "3";
    String PAYMENT_POR_CHEQUE = "5";
    String PAYMENT_WELLET = "10";
}
