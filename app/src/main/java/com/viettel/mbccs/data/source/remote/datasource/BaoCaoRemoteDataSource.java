package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IBaoCaoRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoCaoRemoteDataSource implements IBaoCaoRemoteDataSource {
    private volatile static BaoCaoRemoteDataSource instance;

    private BaoCaoRemoteDataSource() {

    }

    public static BaoCaoRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BaoCaoRemoteDataSource();
        }
        return instance;
    }
}
