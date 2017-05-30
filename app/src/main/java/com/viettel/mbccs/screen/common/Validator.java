package com.viettel.mbccs.screen.common;

/**
 * Created by minhnx on 5/30/17.
 */

public class Validator {
    public static boolean isSerialValid(String serial){
        try{

            if(serial == null || serial.length() < 5 || serial.length() > 30)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        try{

            if(phoneNumber == null || phoneNumber.length() < 8 || phoneNumber.length() > 12)
                return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
}
