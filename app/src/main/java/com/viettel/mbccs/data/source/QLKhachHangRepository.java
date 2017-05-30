package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.datasource.QLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.local.IQLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.remote.datasource.QLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLKhachHangRepository
        implements IQLKhachHangLocalDataSource, IQLKhachHangRemoteDataSource {
    private volatile static QLKhachHangRepository instance;
    private IQLKhachHangLocalDataSource qLKhachHangLocalDataSource;
    private IQLKhachHangRemoteDataSource qLKhachHangRemoteDataSource;

    public static QLKhachHangRepository getInstance() {
        if (instance == null) {
            instance = new QLKhachHangRepository(
                    QLKhachHangLocalDataSource.getInstance(),
                    QLKhachHangRemoteDataSource.getInstance());
        }
        return instance;
    }

    public QLKhachHangRepository(
            QLKhachHangLocalDataSource qLKhachHangLocalDataSource,
            QLKhachHangRemoteDataSource qLKhachHangRemoteDataSource) {
        this.qLKhachHangLocalDataSource = qLKhachHangLocalDataSource;
        this.qLKhachHangRemoteDataSource = qLKhachHangRemoteDataSource;
    }
}
