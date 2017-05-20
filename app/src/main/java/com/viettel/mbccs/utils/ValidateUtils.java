package com.viettel.mbccs.utils;

import android.text.TextUtils;

public class ValidateUtils {

    public static boolean isPhoneNumber(String s) {
        return !TextUtils.isEmpty(s) && android.util.Patterns.PHONE.matcher(s).matches();
    }
}
