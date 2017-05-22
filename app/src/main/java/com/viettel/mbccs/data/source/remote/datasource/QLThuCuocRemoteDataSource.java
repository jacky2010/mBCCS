package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLThuCuocRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLThuCuocRemoteDataSource implements IQLThuCuocRemoteDataSource {
    private volatile static QLThuCuocRemoteDataSource instance;

    public static QLThuCuocRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new QLThuCuocRemoteDataSource();
        }
        return instance;
    }
}
