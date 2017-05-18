package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRemoteDataSource implements IUserRemoteDataSource {

    public static UserRemoteDataSource instance;

    public UserRemoteDataSource() {

    }

    public static UserRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        return RequestHelper.getRequest()
                .login(loginRequest)
                .flatMap(SchedulerUtils.<LoginResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<LoginResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<Object> sendCodeChangePass(String phone) {
        return RequestHelper.getRequest()
                .sendCodeChangePass(phone)
                .flatMap(SchedulerUtils.<Object>convertDataFlatMap())
                .compose(SchedulerUtils.<Object>applyAsyncSchedulers());
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            BaseRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return RequestHelper.getRequest()
                .getTelecomserviceAndSaleProgram(request)
                .flatMap(SchedulerUtils.<TelecomServiceAndSaleProgramResponse>convertDataFlatMap());
    }

    @Override
    public Observable<GetSerialsReponse> getSerial(BaseRequest<GetSerialRequest> request) {
        return RequestHelper.getRequest()
                .getSerials(request)
                .flatMap(SchedulerUtils.<GetSerialsReponse>convertDataFlatMap());
    }
}
