package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.CheckCalledIsdnResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateRegisterSubResponse;
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
    public Observable<GetRegisterSubResponse> getRegisterSub(DataRequest<GetRegisterSubRequest> request) {
        return RequestHelper.getRequest()
                .getRegisterSub(request)
                .flatMap(SchedulerUtils.<GetRegisterSubResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetRegisterSubResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CheckCalledIsdnResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnRequest> request) {
        return RequestHelper.getRequest()
                .checkCalledIsdn(request)
                .flatMap(SchedulerUtils.<CheckCalledIsdnResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CheckCalledIsdnResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<UpdateRegisterSubResponse> updateRegisterSub(DataRequest<UpdateRegisterSubRequest> request) {
        return RequestHelper.getRequest()
                .updateRegisterSub(request)
                .flatMap(SchedulerUtils.<UpdateRegisterSubResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<UpdateRegisterSubResponse>applyAsyncSchedulers());
    }

    public static ChangeSimRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ChangeSimRemoteDataSource();
        }
        return instance;
    }
}
