package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IBanHangKhoTaiChinhLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BanHangKhoTaiChinhLocalDataSource implements IBanHangKhoTaiChinhLocalDataSource {
    private volatile static BanHangKhoTaiChinhLocalDataSource instance;
    private SharedPrefs sharedPrefs;
    private Gson gson;

    private BanHangKhoTaiChinhLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static BanHangKhoTaiChinhLocalDataSource getInstance() {
        if (instance == null) {
            instance = new BanHangKhoTaiChinhLocalDataSource();
        }
        return instance;
    }
}
