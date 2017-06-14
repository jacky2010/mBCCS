package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.ISellAnyPayLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.SellAnyPayLocalDataSource;
import com.viettel.mbccs.data.source.remote.ISellAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.SellAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToChannelRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToCustomerRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToChannelResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToCustomerResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SellAnyPayRepository implements ISellAnyPayLocalDataSource, ISellAnyPayRemoteDataSource {

    private volatile static SellAnyPayRepository instance;
    private SellAnyPayLocalDataSource localDataSource;
    private SellAnyPayRemoteDataSource remoteDataSource;

    public SellAnyPayRepository(SellAnyPayLocalDataSource localDataSource,
                                SellAnyPayRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SellAnyPayRepository getInstance() {
        if (instance == null) {
            instance = new SellAnyPayRepository(SellAnyPayLocalDataSource.getInstance(),
                    SellAnyPayRemoteDataSource.getInstance());
        }
        return instance;
    }


    @Override
    public List<KeyValue> getCustTypes() {
        return localDataSource.getCustTypes();
    }

    @Override
    public List<KeyValue> getPayMethods() {
        return localDataSource.getPayMethods();
    }

    @Override
    public List<KeyValue> getDefaultAmounts() {
        return localDataSource.getDefaultAmounts();
    }

    @Override
    public List<KeyValue> getBankPlusAmounts() {
        return localDataSource.getBankPlusAmounts();
    }

    @Override
    public Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request) {
        return remoteDataSource.getAnypayAuthorize(request);
    }

    @Override
    public Observable<SellAnypayToCustomerResponse> sellAnypayToCustomer(DataRequest<SellAnypayToCustomerRequest> request) {
        return remoteDataSource.sellAnypayToCustomer(request);
    }

    @Override
    public Observable<SellAnypayToChannelResponse> sellAnypayToChannel(DataRequest<SellAnypayToChannelRequest> request) {
        return remoteDataSource.sellAnypayToChannel(request);
    }

    @Override
    public List<KeyValue> getBranches() {
        return localDataSource.getBranches();
    }

    @Override
    public List<KeyValue> getManagers() {
        return localDataSource.getManagers();
    }

    @Override
    public List<KeyValue> getChannels() {
        return localDataSource.getChannels();
    }
}
