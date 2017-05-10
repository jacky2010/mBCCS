package com.viettel.mbccs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.viettel.mbccs.MBCCSApplication;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Common {
    public static boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) MBCCSApplication.self()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null || connectivity.getAllNetworkInfo() == null) return false;
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        for (NetworkInfo anInfo : info) {
            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    private static String md5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            String hash = new BigInteger(1, m.digest()).toString(16);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
