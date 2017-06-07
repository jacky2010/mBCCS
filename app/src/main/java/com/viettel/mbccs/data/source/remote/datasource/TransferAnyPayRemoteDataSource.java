package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ITransferAnyPayRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.PayTransferAnypayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.PayTransferAnypayResponse;
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
    public Observable<PayTransferAnypayResponse> payTransferAnypay(DataRequest<PayTransferAnypayRequest> request) {
        return RequestHelper.getRequest()
                .payTransferAnypay(request)
                .flatMap(SchedulerUtils.<PayTransferAnypayResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<PayTransferAnypayResponse>applyAsyncSchedulers());
    }

    public static TransferAnyPayRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new TransferAnyPayRemoteDataSource();
        }
        return instance;
    }
}
