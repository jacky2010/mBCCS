package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRemoteDataSource implements IUserRemoteDataSource {

    public volatile static UserRemoteDataSource instance;

    public UserRemoteDataSource() {

    }

    public static UserRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<LoginInfo> login(LoginRequest loginRequest) {
        return RequestHelper.getRequest()
                .login(loginRequest)
                .flatMap(new Func1<LoginInfo, Observable<LoginInfo>>() {
                    @Override
                    public Observable<LoginInfo> call(LoginInfo loginInfo) {
                        // TODO: 23/05/2017 Need error handler
                        if (loginInfo == null) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(Integer.parseInt("000"), "Login failed.");
                            return Observable.error(BaseException.toServerError(baseErrorResponse));
                        } else {
                            return Observable.just(loginInfo);
                        }
                    }
                })
                .compose(SchedulerUtils.<LoginInfo>applyAsyncSchedulers());
    }

    @Override
    public Observable<SendCodeChangePassResponse> sendCodeChangePass(String phone) {
        return RequestHelper.getRequest()
                .sendCodeChangePass(phone)
                .flatMap(SchedulerUtils.<SendCodeChangePassResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SendCodeChangePassResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            BaseRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return RequestHelper.getRequest()
                .getTelecomserviceAndSaleProgram(request)
                .flatMap(SchedulerUtils.<TelecomServiceAndSaleProgramResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<TelecomServiceAndSaleProgramResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetSerialsResponse> getSerial(BaseRequest<GetSerialRequest> request) {
        return RequestHelper.getRequest()
                .getSerials(request)
                .flatMap(SchedulerUtils.<GetSerialsResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetSerialsResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetTotalStockResponse> getModelSales(BaseRequest<GetTotalStockRequest> request) {
        return RequestHelper.getRequest()
                .getModelSales(request)
                .flatMap(SchedulerUtils.<GetTotalStockResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetTotalStockResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetInfoSaleTranResponse> getSaleTransInfo(BaseRequest<GetInfoSaleTranRequest> request) {
        return RequestHelper.getRequest()
                .getSaleTransInfo(request)
                .flatMap(SchedulerUtils.<GetInfoSaleTranResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetInfoSaleTranResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetInfoSaleTranResponse> createSaleTransRetail(
            BaseRequest<GetInfoSaleTranRequest> request) {
        return RequestHelper.getRequest()
                .createSaleTransRetail(request)
                .flatMap(SchedulerUtils.<GetInfoSaleTranResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetInfoSaleTranResponse>applyAsyncSchedulers());
    }


}