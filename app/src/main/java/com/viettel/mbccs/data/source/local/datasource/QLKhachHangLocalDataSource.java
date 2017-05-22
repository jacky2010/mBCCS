package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IQLKhachHangLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLKhachHangLocalDataSource implements IQLKhachHangLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static QLKhachHangLocalDataSource instance;

    public QLKhachHangLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static QLKhachHangLocalDataSource getInstance() {
        if (instance == null) {
            instance = new QLKhachHangLocalDataSource();
        }
        return instance;
    }
}
