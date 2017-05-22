package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IBaoHanhCSKHLocalDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoHanhCSKHLocalDataSource implements IBaoHanhCSKHLocalDataSource {
    private volatile static BaoHanhCSKHLocalDataSource instance;
    private Gson gson;
    private SharedPrefs sharedPrefs;

    public BaoHanhCSKHLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static BaoHanhCSKHLocalDataSource getInstance() {
        if (instance == null) {
            instance = new BaoHanhCSKHLocalDataSource();
        }
        return instance;
    }
}
