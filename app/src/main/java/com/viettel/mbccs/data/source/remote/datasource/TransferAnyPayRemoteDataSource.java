package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ITransferAnyPayRemoteDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class TransferAnyPayRemoteDataSource implements ITransferAnyPayRemoteDataSource {

    public volatile static TransferAnyPayRemoteDataSource instance;

    public TransferAnyPayRemoteDataSource() {

    }

    public static TransferAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new TransferAnyPayRemoteDataSource();
        }
        return instance;
    }
}
