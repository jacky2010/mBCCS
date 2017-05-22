package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IQLThuCuocLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLThuCuocLocalDataSource implements IQLThuCuocLocalDataSource {
    private volatile static QLThuCuocLocalDataSource instance;
    private SharedPrefs sharedPrefs;
    private Gson gson;

    private QLThuCuocLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static QLThuCuocLocalDataSource getInstance() {
        if (instance == null) {
            instance = new QLThuCuocLocalDataSource();
        }
        return instance;
    }
}
