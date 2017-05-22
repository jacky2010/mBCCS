package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IQLDiaBanLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.QLDiaBanLocalDataSource;
import com.viettel.mbccs.data.source.remote.IQLDiaBanRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.QLDiaBanRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLDiaBanRepository implements IQLDiaBanLocalDataSource, IQLDiaBanRemoteDataSource {

    private volatile static QLDiaBanRepository instance;
    private IQLDiaBanLocalDataSource qLDiaBanLocalDataSource;
    private IQLDiaBanRemoteDataSource qLDiaBanRemoteDataSource;

    private QLDiaBanRepository(QLDiaBanLocalDataSource qLDiaBanLocalDataSource,
            QLDiaBanRemoteDataSource qLDiaBanRemoteDataSource) {
        this.qLDiaBanLocalDataSource = qLDiaBanLocalDataSource;
        this.qLDiaBanRemoteDataSource = qLDiaBanRemoteDataSource;
    }

    public static QLDiaBanRepository getInstance() {
        if (instance == null) {
            instance = new QLDiaBanRepository(QLDiaBanLocalDataSource.getInstance(),
                    QLDiaBanRemoteDataSource.getInstance());
        }
        return instance;
    }
}
