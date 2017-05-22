package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IQLDiaBanLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLDiaBanLocalDataSource implements IQLDiaBanLocalDataSource {
    private volatile static QLDiaBanLocalDataSource instance;
    private Gson gson;
    private SharedPrefs sharedPrefs;

    private QLDiaBanLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static QLDiaBanLocalDataSource getInstance() {
        if (instance == null){
            instance = new QLDiaBanLocalDataSource();
        }
        return instance;
    }
}
