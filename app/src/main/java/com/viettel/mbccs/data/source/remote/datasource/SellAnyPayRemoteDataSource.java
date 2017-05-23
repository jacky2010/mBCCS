package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ISellAnyPayRemoteDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SellAnyPayRemoteDataSource implements ISellAnyPayRemoteDataSource {

    public volatile static SellAnyPayRemoteDataSource instance;

    public SellAnyPayRemoteDataSource() {

    }

    public static SellAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new SellAnyPayRemoteDataSource();
        }
        return instance;
    }
}
