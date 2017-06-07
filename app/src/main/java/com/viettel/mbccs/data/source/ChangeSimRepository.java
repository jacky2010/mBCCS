package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.ChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.ChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.CheckCalledIsdnResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateRegisterSubResponse;

import java.util.List;

import rx.Observable;

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

    @Override
    public Observable<GetRegisterSubResponse> getRegisterSub(DataRequest<GetRegisterSubRequest> request) {
        return remoteDataSource.getRegisterSub(request);
    }

    @Override
    public Observable<CheckCalledIsdnResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnRequest> request) {
        return remoteDataSource.checkCalledIsdn(request);
    }

    @Override
    public Observable<UpdateRegisterSubResponse> updateRegisterSub(DataRequest<UpdateRegisterSubRequest> request) {
        return remoteDataSource.updateRegisterSub(request);
    }
}
