package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLKhachHangRemoteDataSource implements IQLKhachHangRemoteDataSource {
    public volatile static QLKhachHangRemoteDataSource instance;

    public QLKhachHangRemoteDataSource() {

    }

    public static QLKhachHangRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new QLKhachHangRemoteDataSource();
        }
        return instance;
    }
}
