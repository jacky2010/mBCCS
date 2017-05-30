package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.ChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.ChangeSimRemoteDataSource;

import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ChangeSimRepository implements IChangeSimLocalDataSource, IChangeSimRemoteDataSource {

    private volatile static ChangeSimRepository instance;
    private ChangeSimLocalDataSource localDataSource;
    private ChangeSimRemoteDataSource remoteDataSource;

    public ChangeSimRepository(ChangeSimLocalDataSource localDataSource,
                               ChangeSimRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static ChangeSimRepository getInstance() {
        if (instance == null) {
            instance = new ChangeSimRepository(ChangeSimLocalDataSource.getInstance(),
                    ChangeSimRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public List<KeyValue> getDocumentTypes() {
        return localDataSource.getDocumentTypes();
    }

    @Override
    public double getChangeSimPrice() {
        return localDataSource.getChangeSimPrice();
    }

    @Override
    public double getChangeSimServiceFee() {
        return localDataSource.getChangeSimServiceFee();
    }
}
