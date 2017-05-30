package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IChangeSimLocalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ChangeSimLocalDataSource implements IChangeSimLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static ChangeSimLocalDataSource instance;

    public ChangeSimLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    @Override
    public List<KeyValue> getDocumentTypes() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item = new KeyValue("0", "Số chứng minh thư");
        result.add(item);

        item = new KeyValue("0", "Số hợp đồng");
        result.add(item);

        return result;
    }

    public static ChangeSimLocalDataSource getInstance() {
        if (instance == null) {
            instance = new ChangeSimLocalDataSource();
        }
        return instance;
    }

    @Override
    public double getChangeSimPrice() {
        return 19000;
    }

    @Override
    public double getChangeSimServiceFee() {
        return 0;
    }
}
