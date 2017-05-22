package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IBaoCaoLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoCaoLocalDataSource implements IBaoCaoLocalDataSource{
    private volatile static BaoCaoLocalDataSource instance;
    private Gson gson;
    private SharedPrefs sharedPrefs;

    private BaoCaoLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static BaoCaoLocalDataSource getInstance() {
        if (instance == null){
            instance = new BaoCaoLocalDataSource();
        }
        return instance;
    }
}
