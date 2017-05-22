package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IBaoCaoLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BaoCaoLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBaoCaoRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.BaoCaoRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoCaoRepository implements IBaoCaoLocalDataSource, IBaoCaoRemoteDataSource {

    private volatile static BaoCaoRepository instance;
    private IBaoCaoLocalDataSource baoCaoLocalDataSource;
    private IBaoCaoRemoteDataSource baoCaoRemoteDataSource;

    private BaoCaoRepository(BaoCaoLocalDataSource baoCaoLocalDataSource,
            BaoCaoRemoteDataSource baoCaoRemoteDataSource) {
        this.baoCaoLocalDataSource = baoCaoLocalDataSource;
        this.baoCaoRemoteDataSource = baoCaoRemoteDataSource;
    }

    public static BaoCaoRepository getInstance() {
        if (instance == null) {
            instance = new BaoCaoRepository(BaoCaoLocalDataSource.getInstance(),
                    BaoCaoRemoteDataSource.getInstance());
        }
        return instance;
    }
}
