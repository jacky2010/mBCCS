package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IHotNewsCSKPPLocalDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class HotNewsCSKPPLocalDataSource implements IHotNewsCSKPPLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static HotNewsCSKPPLocalDataSource instance;

    public HotNewsCSKPPLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }


    public static HotNewsCSKPPLocalDataSource getInstance() {
        if (instance == null) {
            instance = new HotNewsCSKPPLocalDataSource();
        }
        return instance;
    }
}
