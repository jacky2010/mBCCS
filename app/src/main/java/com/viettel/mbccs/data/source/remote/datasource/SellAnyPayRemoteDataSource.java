package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ISellAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToChannelRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToCustomerRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToChannelResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToCustomerResponse;
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
    public Observable<SellAnypayToCustomerResponse> sellAnypayToCustomer(DataRequest<SellAnypayToCustomerRequest> request) {
        return RequestHelper.getRequest()
                .saleAnypayToCustomer(request)
                .flatMap(SchedulerUtils.<SellAnypayToCustomerResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SellAnypayToCustomerResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<SellAnypayToChannelResponse> sellAnypayToChannel(DataRequest<SellAnypayToChannelRequest> request) {
        return RequestHelper.getRequest()
                .saleAnypayToChannel(request)
                .flatMap(SchedulerUtils.<SellAnypayToChannelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SellAnypayToChannelResponse>applyAsyncSchedulers());
    }

    public static SellAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new SellAnyPayRemoteDataSource();
        }
        return instance;
    }
}
