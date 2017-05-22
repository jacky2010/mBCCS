package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IBaoHanhCSKHLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BaoHanhCSKHLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBaoHanhCSKHRemoteDataSrource;
import com.viettel.mbccs.data.source.remote.datasource.BaoHanhCSKHRemoteDataSource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoHanhCSKHRepository
        implements IBaoHanhCSKHLocalDataSource, IBaoHanhCSKHRemoteDataSrource {

    private volatile static BaoHanhCSKHRepository instance;
    private IBaoHanhCSKHLocalDataSource baoHanhCSKHLocalDataSrouce;
    private IBaoHanhCSKHRemoteDataSrource baoHanhCSKHRemoteDataSrouce;

    private BaoHanhCSKHRepository(BaoHanhCSKHLocalDataSource baoHanhCSKHLocalDataSrouce,
            BaoHanhCSKHRemoteDataSource baoHanhCSKHRemoteDataSrouce) {
        this.baoHanhCSKHLocalDataSrouce = baoHanhCSKHLocalDataSrouce;
        this.baoHanhCSKHRemoteDataSrouce = baoHanhCSKHRemoteDataSrouce;
    }

    public static BaoHanhCSKHRepository getInstance() {
        if (instance == null) {
            instance = new BaoHanhCSKHRepository(BaoHanhCSKHLocalDataSource.getInstance(),
                    BaoHanhCSKHRemoteDataSource.getInstance());
        }
        return instance;
    }
}
