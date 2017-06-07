package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ITransferAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.RefillAnyPayRequest;
import com.viettel.mbccs.data.source.remote.request.TransferAnyPayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.RefillAnyPayResponse;
import com.viettel.mbccs.data.source.remote.response.TransferAnyPayResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class TransferAnyPayRemoteDataSource implements ITransferAnyPayRemoteDataSource {

    public volatile static TransferAnyPayRemoteDataSource instance;

    public TransferAnyPayRemoteDataSource() {

    }

    @Override
    public Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request) {
        return RequestHelper.getRequest()
                .getAnypayAuthorize(request)
                .flatMap(SchedulerUtils.<GetAnypayAuthorizeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetAnypayAuthorizeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<TransferAnyPayResponse> transferAnyPay(DataRequest<TransferAnyPayRequest> request) {
        return RequestHelper.getRequest()
                .transferAnyPay(request)
                .flatMap(SchedulerUtils.<TransferAnyPayResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<TransferAnyPayResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<RefillAnyPayResponse> refillAnyPay(DataRequest<RefillAnyPayRequest> request) {
        return RequestHelper.getRequest()
                .refillAnyPay(request)
                .flatMap(SchedulerUtils.<RefillAnyPayResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<RefillAnyPayResponse>applyAsyncSchedulers());
    }

    public static TransferAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new TransferAnyPayRemoteDataSource();
        }
        return instance;
    }
}
