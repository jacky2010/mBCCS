package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.ITransferAnyPayLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.TransferAnyPayLocalDataSource;
import com.viettel.mbccs.data.source.remote.ITransferAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.TransferAnyPayRemoteDataSource;

import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class TransferAnyPayRepository implements ITransferAnyPayLocalDataSource, ITransferAnyPayRemoteDataSource {

    private volatile static TransferAnyPayRepository instance;
    private TransferAnyPayLocalDataSource localDataSource;
    private TransferAnyPayRemoteDataSource remoteDataSource;

    public TransferAnyPayRepository(TransferAnyPayLocalDataSource localDataSource,
                                    TransferAnyPayRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static TransferAnyPayRepository getInstance() {
        if (instance == null) {
            instance = new TransferAnyPayRepository(TransferAnyPayLocalDataSource.getInstance(),
                    TransferAnyPayRemoteDataSource.getInstance());
        }
        return instance;
    }


    @Override
    public List<KeyValue> getDefaultAmounts() {
        return localDataSource.getDefaultAmounts();
    }

    @Override
    public List<KeyValue> getTransferTypes() {
        return localDataSource.getTransferTypes();
    }
}
