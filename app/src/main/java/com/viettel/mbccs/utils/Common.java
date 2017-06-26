package com.viettel.mbccs.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockModel;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.local.datasource.SharedPrefs;
import com.viettel.mbccs.screen.login.LoginActivity;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    public static void logout(Context mContext) {
        SharedPrefs sharedPrefs = SharedPrefs.getInstance();
        sharedPrefs.clear();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
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

    public static int getSerialCountByListSerialBlock(List<SerialBO> serialBlock) {
        Set<String> sets = new HashSet<>();
        for (SerialBO s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        return sets.size();
    }

    public static List<String> getSerialsByListSerialBlock(List<SerialBO> serialBlock) {
        Set<String> sets = new HashSet<>();
        for (SerialBO s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        List result = new ArrayList();
        result.addAll(sets);
        return result;
    }

    public static String formatDouble1(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

    public static List<SerialBO> getSerialBlockBySerials(List<String> integers, long stockModelId) {
        if (integers == null) {
            integers = new ArrayList<>();
        }
        List<SerialBO> serialBlocks = new ArrayList<>();
        Collections.sort(integers, new Comparator<String>() {
            @Override
            public int compare(String integer, String t1) {
                return integer.compareTo(t1);
            }
        });
        SerialBO serialBlock = new SerialBO();
        serialBlock.setStockModelId(stockModelId);
        for (String serial : integers) {
            if (serialBlock.getFromSerial().equals("-1")) {
                serialBlock.setFromSerial(serial);
                serialBlock.setToSerial(serial);
            } else {
                if (Long.parseLong(serial) - Long.parseLong(serialBlock.getToSerial()) == 1) {
                    serialBlock.setToSerial(serial);
                } else {
                    serialBlocks.add(
                            new SerialBO(serialBlock.getFromSerial(), serialBlock.getToSerial()));
                    serialBlock.setFromSerial(serial);
                    serialBlock.setToSerial(serial);
                }
            }
        }
        if (!serialBlock.getFromSerial().equals("-1")) {
            serialBlocks.add(serialBlock);
        }
        return serialBlocks;
    }

    public static List<SerialBO> getSerialBlockBySerials(List<String> integers) {
        if (integers == null) {
            integers = new ArrayList<>();
        }
        List<SerialBO> serialBlocks = new ArrayList<>();
        Collections.sort(integers, new Comparator<String>() {
            @Override
            public int compare(String integer, String t1) {
                return integer.compareTo(t1);
            }
        });
        SerialBO serialBlock = new SerialBO();
        for (String serial : integers) {
            if (serialBlock.getFromSerial().equals("-1")) {
                serialBlock.setFromSerial(serial);
                serialBlock.setToSerial(serial);
            } else {
                if (Long.parseLong(serial) - Long.parseLong(serialBlock.getToSerial()) == 1) {
                    serialBlock.setToSerial(serial);
                } else {
                    serialBlocks.add(
                            new SerialBO(serialBlock.getFromSerial(), serialBlock.getToSerial()));
                    serialBlock.setFromSerial(serial);
                    serialBlock.setToSerial(serial);
                }
            }
        }
        if (!serialBlock.getFromSerial().equals("-1")) {
            serialBlocks.add(serialBlock);
        }
        return serialBlocks;
    }

    public static Locale getLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale;
    }

    public static String convertMoneyToString(double money) {
        return numberToString(money);
    }

    public static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals(".")) {
                continue;
            } else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;
    }

    public static String formatDouble(double number) {
        return String.format(Locale.getDefault(), "%,.0f", number);
    }

    public static String numberToString(double number) {
        String sNumber = formatNumberForRead(number);
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if (sReturn.length() > 1) {
            sReturn = sReturn.substring(0, 1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";
        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = {
                "", "ngàn" + " ", "triệu" + " ", "tỷ" + " "
        };
        String sSo[] = {
                "không" + " ", "một" + " ", "hai" + " ", "ba" + " ", "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ", "tám" + " ", "chín" + " "
        };
        String sDonvi[] = {
                "", "mươi" + " ", "trăm" + " "
        };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if (sNumber.length() <= 1) {
                            sRead = "năm" + " ";
                        } else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else {
                            sRead = "năm" + " ";
                        }
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }

        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for (Map.Entry<String, Integer> entry : roman_numerals.entrySet()) {
            int matches = Int / entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }

    public static String repeat(String s, int n) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String getDayByLong(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(new Date(time));
    }

    public static List<StockModel> convertStockTotalsToStockModels(List<StockTotal> stockTotals) {
        List<StockModel> stockModels = new ArrayList<>();
        for (StockTotal stockTotal : stockTotals) {
            StockModel stockModel = new StockModel();
            stockModel.setStockModelId(stockTotal.getStockModelId());
            stockModel.setStockModelCode(stockTotal.getStockModelCode());
            stockModel.setStockModelName(stockTotal.getStockModelName());
            stockModel.setQuantity(stockTotal.getCountChoice());
            stockModels.add(stockModel);
        }
        return stockModels;
    }
}
