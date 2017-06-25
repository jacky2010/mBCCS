package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ChangeSimRemoteDataSource implements IChangeSimRemoteDataSource {

    public volatile static ChangeSimRemoteDataSource instance;

    public ChangeSimRemoteDataSource() {

    }

    @Override
    public Observable<DataResponse> checkDebit(DataRequest<CheckDebitChangeSimRequest> request) {
        return RequestHelper.getRequest()
                .checkChangeSimDebit(request)
                .flatMap(SchedulerUtils.<DataResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<DataResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<DataResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnsRequest> request) {
        return RequestHelper.getRequest()
                .checkCalledIsdnChangeSim(request)
                .flatMap(SchedulerUtils.<DataResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<DataResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<DataResponse> changeSim(DataRequest<ChangeSimRequest> request) {
        return RequestHelper.getRequest()
                .changeSim(request)
                .flatMap(SchedulerUtils.<DataResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<DataResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetRegisterSubInfoResponse> getRegisterSubInfo(DataRequest<GetRegisterSubInfoRequest> request) {
        return RequestHelper.getRequest()
                .getRegiterSubInfo(request)
                .flatMap(SchedulerUtils.<GetRegisterSubInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetRegisterSubInfoResponse>applyAsyncSchedulers());
    }

    public static ChangeSimRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ChangeSimRemoteDataSource();
        }
        return instance;
    }
}
