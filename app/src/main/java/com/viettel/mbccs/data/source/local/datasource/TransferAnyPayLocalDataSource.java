package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.ITransferAnyPayLocalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class TransferAnyPayLocalDataSource implements ITransferAnyPayLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static TransferAnyPayLocalDataSource instance;

    public TransferAnyPayLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    @Override
    public List<KeyValue> getTransferTypes() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item = new KeyValue("0", "Nạp AnyPay");
        result.add(item);

        item = new KeyValue("1", "Chuyển AnyPay");
        result.add(item);

        return result;
    }

    @Override
    public List<KeyValue> getDefaultAmounts() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue(String.valueOf(i+1*1000), (i+1*1000) + "$");

            result.add(item);
        }

        return result;
    }

    public static TransferAnyPayLocalDataSource getInstance() {
        if (instance == null) {
            instance = new TransferAnyPayLocalDataSource();
        }
        return instance;
    }


}
