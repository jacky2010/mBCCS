package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetBankInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetCurrBillCycleRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetListRegTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSubTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetQuotaByProductCodeRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.ConnectSubscriberResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetBankInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetCurrBillCycleResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetListRegTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSubTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetQuotaByProductCodeResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
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
    public Observable<GetRegisterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegisterSubInfoRequest> request) {
        return RequestHelper.getRequest()
                .getRegiterSubInfo(request)
                .flatMap(SchedulerUtils.<GetRegisterSubInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetRegisterSubInfoResponse>applyAsyncSchedulers());
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
    public Observable<GetApDomainByTypeResponse> getApDomainByType(DataRequest<GetApDomainByTypeRequest> request) {
        return RequestHelper.getRequest()
                .getApDomainByType(request)
                .flatMap(SchedulerUtils.<GetApDomainByTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetApDomainByTypeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request) {
        return RequestHelper.getRequest()
                .getOTP(request)
                .flatMap(SchedulerUtils.<GetOTPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetOTPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CheckOTPResponse> checOTP(DataRequest<ChecOTPRequest> request) {
        return RequestHelper.getRequest()
                .checOTP(request)
                .flatMap(SchedulerUtils.<CheckOTPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CheckOTPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetAllSubInfoResponse> getAllSubInfo(
            DataRequest<GetAllSubInfoRequest> request) {
        return RequestHelper.getRequest()
                .getAllSubInfo(request)
                .flatMap(SchedulerUtils.<GetAllSubInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetAllSubInfoResponse>applyAsyncSchedulers());
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

    @Override
    public Observable<GetListProductResponse> getListProduct(
            DataRequest<GetListProductRequest> request) {
        return RequestHelper.getRequest()
                .getListProduct(request)
                .flatMap(SchedulerUtils.<GetListProductResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListProductResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListRegTypeResponse> getListRegType(
            DataRequest<GetListRegTypeRequest> request) {
        return RequestHelper.getRequest()
                .getListRegType(request)
                .flatMap(SchedulerUtils.<GetListRegTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListRegTypeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListSubTypeResponse> getListSubType(
            DataRequest<GetListSubTypeRequest> request) {
        return RequestHelper.getRequest()
                .getListSubType(request)
                .flatMap(SchedulerUtils.<GetListSubTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListSubTypeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ConnectSubscriberResponse> connectSubscriber(
            DataRequest<ConnectSubscriberRequest> request) {
        return RequestHelper.getRequest()
                .connectSubscriber(request)
                .flatMap(SchedulerUtils.<ConnectSubscriberResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ConnectSubscriberResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListBusTypeResponse> getListBusType(
            DataRequest<GetListBusTypeRequest> request) {
        return RequestHelper.getRequest()
                .getListBusType(request)
                .flatMap(SchedulerUtils.<GetListBusTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListBusTypeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetQuotaByProductCodeResponse> getQuotaByProductCode(
            DataRequest<GetQuotaByProductCodeRequest> request) {
        return RequestHelper.getRequest()
                .getQuotaByProductCode(request)
                .flatMap(SchedulerUtils.<GetQuotaByProductCodeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetQuotaByProductCodeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetBankInfoResponse> getBankInfo(
            DataRequest<GetBankInfoRequest> request) {
        return RequestHelper.getRequest()
                .getBankInfo(request)
                .flatMap(SchedulerUtils.<GetBankInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetBankInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetCurrBillCycleResponse> getCurrBillCycle(
            DataRequest<GetCurrBillCycleRequest> request) {
        return RequestHelper.getRequest()
                .getCurrBillCycle(request)
                .flatMap(SchedulerUtils.<GetCurrBillCycleResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetCurrBillCycleResponse>applyAsyncSchedulers());
    }
}
