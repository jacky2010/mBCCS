package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.ChecOTPResponse;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLKhachHangRemoteDataSource implements IQLKhachHangRemoteDataSource {
    public volatile static QLKhachHangRemoteDataSource instance;

    public QLKhachHangRemoteDataSource() {

    }

    public static QLKhachHangRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new QLKhachHangRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetRegiterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegiterSubInfoRequest> request) {
        return RequestHelper.getRequest()
                .getRegiterSubInfo(request)
                .flatMap(SchedulerUtils.<GetRegiterSubInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetRegiterSubInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<RegisterCustomerInfoResponse> registerCustomerInfo(
            DataRequest<RegisterCustomerInfoRequest> request) {
        return RequestHelper.getRequest()
                .registerCustomerInfo(request)
                .flatMap(SchedulerUtils.<RegisterCustomerInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<RegisterCustomerInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListBusTypeIdRequireResponse> getListBusTypeIdRequire(
            DataRequest<GetListBusTypeIdRequireRequest> request) {
        return RequestHelper.getRequest()
                .getListBusTypeIdRequire(request)
                .flatMap(SchedulerUtils.<GetListBusTypeIdRequireResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListBusTypeIdRequireResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetApDomainResponse> getApDomain(DataRequest<GetApDomainRequest> request) {
        return RequestHelper.getRequest()
                .getApDomain(request)
                .flatMap(SchedulerUtils.<GetApDomainResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetApDomainResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request) {
        return RequestHelper.getRequest()
                .getOTP(request)
                .flatMap(SchedulerUtils.<GetOTPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetOTPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ChecOTPResponse> checOTP(DataRequest<ChecOTPRequest> request) {
        return RequestHelper.getRequest()
                .checOTP(request)
                .flatMap(SchedulerUtils.<ChecOTPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ChecOTPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<UpdateAllSubInfoResponse> updateAllSubInfo(
            DataRequest<UpdateAllSubInfoRequest> request) {
        return RequestHelper.getRequest()
                .updateAllSubInfo(request)
                .flatMap(SchedulerUtils.<UpdateAllSubInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<UpdateAllSubInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CheckIdNoResponse> checkIdNo(DataRequest<CheckIdNoRequest> request) {
        return RequestHelper.getRequest()
                .checkIdNo(request)
                .flatMap(SchedulerUtils.<CheckIdNoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CheckIdNoResponse>applyAsyncSchedulers());
    }
}
