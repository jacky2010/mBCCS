package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLDiaBanRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLDiaBanRemoteDataSource implements IQLDiaBanRemoteDataSource {
    private volatile static QLDiaBanRemoteDataSource instance;

    private QLDiaBanRemoteDataSource() {

    }

    public static QLDiaBanRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new QLDiaBanRemoteDataSource();
        }
        return instance;
    }
}
