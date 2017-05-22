package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IQLThuCuocLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.QLThuCuocLocalDataSource;
import com.viettel.mbccs.data.source.remote.IQLThuCuocRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.QLThuCuocRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLThuCuocRepository implements IQLThuCuocLocalDataSource, IQLThuCuocRemoteDataSource {
    private volatile static QLThuCuocRepository instance;
    private IQLThuCuocLocalDataSource qLThuCuocLocalDataSource;
    private IQLThuCuocRemoteDataSource qLThuCuocRemoteDataSource;

    public static QLThuCuocRepository getInstance() {
        if (instance == null) {
            instance = new QLThuCuocRepository(QLThuCuocLocalDataSource.getInstance(),
                    QLThuCuocRemoteDataSource.getInstance());
        }
        return instance;
    }

    public QLThuCuocRepository(QLThuCuocLocalDataSource qLThuCuocLocalDataSource,
            IQLThuCuocRemoteDataSource qLKhachHangRemoteDataSource) {
        this.qLThuCuocLocalDataSource = qLThuCuocLocalDataSource;
        this.qLThuCuocRemoteDataSource = qLKhachHangRemoteDataSource;
    }
}
