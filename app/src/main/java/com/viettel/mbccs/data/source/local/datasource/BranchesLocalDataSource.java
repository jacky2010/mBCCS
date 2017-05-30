package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IBranchesLocalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BranchesLocalDataSource implements IBranchesLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static BranchesLocalDataSource instance;

    public BranchesLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    @Override
    public List<KeyValue> getChannelTypes() {

        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("CHANNEL_" + i+1, "Channel " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getDocumentTypes() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("DOCUMENT_TYPE" + i+1, "Document Type " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getStaffs() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("STAFF_" + i+1, "Staff " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getBtses() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("BTS_" + i+1, "Bts " + i+1);

            result.add(item);
        }

        return result;
    }

    public static BranchesLocalDataSource getInstance() {
        if (instance == null) {
            instance = new BranchesLocalDataSource();
        }
        return instance;
    }


}
