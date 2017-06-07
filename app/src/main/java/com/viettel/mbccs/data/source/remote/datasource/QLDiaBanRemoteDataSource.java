package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLDiaBanRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;

import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLDiaBanRemoteDataSource implements IQLDiaBanRemoteDataSource {
    private volatile static QLDiaBanRemoteDataSource instance;

    private QLDiaBanRemoteDataSource() {

    }

    public static QLDiaBanRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new QLDiaBanRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(DataRequest<GetListProvinceRequest> request) {
        return null;
    }
}
