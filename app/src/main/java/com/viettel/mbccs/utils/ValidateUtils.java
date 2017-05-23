package com.viettel.mbccs.utils;

import android.text.TextUtils;

public class ValidateUtils {

    public static boolean isPhoneNumber(String s) {
        return !TextUtils.isEmpty(s) && android.util.Patterns.PHONE.matcher(s).matches();
    }

    public static boolean isTimeFromToValid(long fromDate, long toDate) {
        return fromDate <= toDate;
    }

    public static boolean isTimeForDay(long fromDate, long toDate, int day) {
        return (toDate - fromDate) / (24 * 60 * 60 * 1000) <= day;
    }
}
