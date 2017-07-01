package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IApDomainLocalDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ApDomainLocalDataSource implements IApDomainLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static ApDomainLocalDataSource instance;

    public ApDomainLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static ApDomainLocalDataSource getInstance() {
        if (instance == null) {
            instance = new ApDomainLocalDataSource();
        }
        return instance;
    }


}
