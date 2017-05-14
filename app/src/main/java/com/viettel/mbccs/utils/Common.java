package com.viettel.mbccs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.SerialBlock;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static int getSerialCountByListSerialBlock(List<SerialBlock> serialBlock) {
        Set<Integer> sets = new HashSet<>();
        for (SerialBlock s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        return sets.size();
    }

    public static List<Integer> getSerialsByListSerialBlock(List<SerialBlock> serialBlock) {
        Set<Integer> sets = new HashSet<>();
        for (SerialBlock s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        List result = new ArrayList();
        result.addAll(sets);
        return result;
    }

    public static List<SerialBlock> getSerialBlockBySerials(List<Integer> integers) {
        if (integers == null) {
            integers = new ArrayList<>();
        }
        List<SerialBlock> serialBlocks = new ArrayList<>();
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        });
        SerialBlock serialBlock = new SerialBlock();
        for (Integer serial : integers) {
            if (serialBlock.getFrom() == -1) {
                serialBlock.setFrom(serial);
                serialBlock.setTo(serial);
            } else {
                if (serial - serialBlock.getTo() == 1) {
                    serialBlock.setTo(serial);
                } else {
                    serialBlocks.add(new SerialBlock(serialBlock.getFrom(), serialBlock.getTo()));
                    serialBlock.setFrom(serial);
                    serialBlock.setTo(serial);
                }
            }
        }
        if (serialBlock.getFrom() != -1) {
            serialBlocks.add(serialBlock);
        }

        return serialBlocks;
    }
}
