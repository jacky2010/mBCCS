package com.viettel.mbccs.data.source.local;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.datasource.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SellAnyPayLocalDataSource implements ISellAnyPayLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static SellAnyPayLocalDataSource instance;

    public SellAnyPayLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    @Override
    public List<KeyValue> getCustTypes() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("CUST_TYPE_" + i+1, "Customer Type " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getPayMethods() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("PAY_METHOD" + i+1, "Pay Method " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getDefaultAmounts() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("DEF_AMOUNT" + i+1, (i+1) + "$");

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getBankPlusAmounts() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("BANK_AMOUNT" + i+1, ((i+1) * 10) + "$");

            result.add(item);
        }

        return result;
    }

    public static SellAnyPayLocalDataSource getInstance() {
        if (instance == null) {
            instance = new SellAnyPayLocalDataSource();
        }
        return instance;
    }


}
