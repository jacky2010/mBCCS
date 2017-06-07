package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ISellAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.SaleAnypayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.SaleAnypayResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SellAnyPayRemoteDataSource implements ISellAnyPayRemoteDataSource {

    public volatile static SellAnyPayRemoteDataSource instance;

    public SellAnyPayRemoteDataSource() {

    }

    @Override
    public Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request) {
        return RequestHelper.getRequest()
                .getAnypayAuthorize(request)
                .flatMap(SchedulerUtils.<GetAnypayAuthorizeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetAnypayAuthorizeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<SaleAnypayResponse> saleAnypay(DataRequest<SaleAnypayRequest> request) {
        return RequestHelper.getRequest()
                .saleAnypay(request)
                .flatMap(SchedulerUtils.<SaleAnypayResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SaleAnypayResponse>applyAsyncSchedulers());
    }

    public static SellAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new SellAnyPayRemoteDataSource();
        }
        return instance;
    }
}
