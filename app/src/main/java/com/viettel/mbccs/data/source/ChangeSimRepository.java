package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.ChangeSimLocalDataSource;
import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.ChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;

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
    public Observable<DataResponse> checkDebit(DataRequest<CheckDebitChangeSimRequest> request) {
        return remoteDataSource.checkDebit(request);
    }

    @Override
    public Observable<DataResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnsRequest> request) {
        return remoteDataSource.checkCalledIsdn(request);
    }

    @Override
    public Observable<DataResponse> changeSim(DataRequest<ChangeSimRequest> request) {
        return remoteDataSource.changeSim(request);
    }

    @Override
    public Observable<GetRegiterSubInfoResponse> getRegiterSubInfo(DataRequest<GetRegiterSubInfoRequest> request) {
        return remoteDataSource.getRegiterSubInfo(request);
    }
}
