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

    public static boolean isChannelValid(String channelCode){
        try{

            if(channelCode == null || channelCode.trim().length() < 3 || channelCode.trim().length() > 30)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isBankAccountValid(String channelCode){
        try{

            if(channelCode == null || channelCode.trim().length() < 9 || channelCode.trim().length() > 20)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isAmountValid(String amount){
        try{
            if(amount == null || "".equals(amount.trim()))
                return false;

            Double.parseDouble(amount.trim());
            return true;
        }catch (NumberFormatException ne){
            return false;
        }
    }

    public static boolean isSerialValid(String serial){
        try{

            if(serial == null || serial.trim().length() < 5 || serial.trim().length() > 30)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        try{

            if(phoneNumber == null || phoneNumber.trim().length() < 8 || phoneNumber.trim().length() > 12)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
}
