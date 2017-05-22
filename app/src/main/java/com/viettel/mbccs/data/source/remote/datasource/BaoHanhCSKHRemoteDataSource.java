package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IBaoHanhCSKHRemoteDataSrource;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BaoHanhCSKHRemoteDataSource implements IBaoHanhCSKHRemoteDataSrource {
    public volatile static BaoHanhCSKHRemoteDataSource instance;

    public BaoHanhCSKHRemoteDataSource() {

    }

    public static BaoHanhCSKHRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BaoHanhCSKHRemoteDataSource();
        }
        return instance;
    }
}
