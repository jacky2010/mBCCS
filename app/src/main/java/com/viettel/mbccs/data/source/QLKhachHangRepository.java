package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IQLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.QLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.QLKhachHangRemoteDataSource;
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
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class QLKhachHangRepository
        implements IQLKhachHangLocalDataSource, IQLKhachHangRemoteDataSource {
    private volatile static QLKhachHangRepository instance;
    private IQLKhachHangLocalDataSource qLKhachHangLocalDataSource;
    private IQLKhachHangRemoteDataSource qLKhachHangRemoteDataSource;

    public static QLKhachHangRepository getInstance() {
        if (instance == null) {
            instance = new QLKhachHangRepository(QLKhachHangLocalDataSource.getInstance(),
                    QLKhachHangRemoteDataSource.getInstance());
        }
        return instance;
    }

    public QLKhachHangRepository(QLKhachHangLocalDataSource qLKhachHangLocalDataSource,
            QLKhachHangRemoteDataSource qLKhachHangRemoteDataSource) {
        this.qLKhachHangLocalDataSource = qLKhachHangLocalDataSource;
        this.qLKhachHangRemoteDataSource = qLKhachHangRemoteDataSource;
    }

    @Override
    public Observable<GetRegisterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegisterSubInfoRequest> request) {
        return qLKhachHangRemoteDataSource.getRegiterSubInfo(request);
    }

    @Override
    public Observable<RegisterCustomerInfoResponse> registerCustomerInfo(
            DataRequest<RegisterCustomerInfoRequest> request) {
        return qLKhachHangRemoteDataSource.registerCustomerInfo(request);
    }

    @Override
    public Observable<GetListBusTypeIdRequireResponse> getListBusTypeIdRequire(
            DataRequest<GetListBusTypeIdRequireRequest> request) {
        return qLKhachHangRemoteDataSource.getListBusTypeIdRequire(request);
    }

    @Override
    public Observable<GetApDomainByTypeResponse> getApDomainByType(DataRequest<GetApDomainByTypeRequest> request) {
        return qLKhachHangRemoteDataSource.getApDomainByType(request);
    }

    @Override
    public Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request) {
        return qLKhachHangRemoteDataSource.getOTP(request);
    }

    @Override
    public Observable<CheckOTPResponse> checOTP(DataRequest<ChecOTPRequest> request) {
        return qLKhachHangRemoteDataSource.checOTP(request);
    }

    @Override
    public Observable<GetAllSubInfoResponse> getAllSubInfo(
            DataRequest<GetAllSubInfoRequest> request) {
        return qLKhachHangRemoteDataSource.getAllSubInfo(request);
    }

    @Override
    public Observable<UpdateAllSubInfoResponse> updateAllSubInfo(
            DataRequest<UpdateAllSubInfoRequest> request) {
        return qLKhachHangRemoteDataSource.updateAllSubInfo(request);
    }

    @Override
    public Observable<CheckIdNoResponse> checkIdNo(DataRequest<CheckIdNoRequest> request) {
        return qLKhachHangRemoteDataSource.checkIdNo(request);
    }

    @Override
    public Observable<GetListProductResponse> getListProduct(
            DataRequest<GetListProductRequest> request) {
        return qLKhachHangRemoteDataSource.getListProduct(request);
    }

    @Override
    public Observable<GetListRegTypeResponse> getListRegType(
            DataRequest<GetListRegTypeRequest> request) {
        return qLKhachHangRemoteDataSource.getListRegType(request);
    }

    @Override
    public Observable<GetListSubTypeResponse> getListSubType(
            DataRequest<GetListSubTypeRequest> request) {
        return qLKhachHangRemoteDataSource.getListSubType(request);
    }

    @Override
    public Observable<ConnectSubscriberResponse> connectSubscriber(
            DataRequest<ConnectSubscriberRequest> request) {
        return qLKhachHangRemoteDataSource.connectSubscriber(request);
    }

    @Override
    public Observable<GetListBusTypeResponse> getListBusType(
            DataRequest<GetListBusTypeRequest> request) {
        return qLKhachHangRemoteDataSource.getListBusType(request);
    }

    @Override
    public Observable<GetQuotaByProductCodeResponse> getQuotaByProductCode(
            DataRequest<GetQuotaByProductCodeRequest> request) {
        return qLKhachHangRemoteDataSource.getQuotaByProductCode(request);
    }

    @Override
    public Observable<GetBankInfoResponse> getBankInfo(DataRequest<GetBankInfoRequest> request) {
        return qLKhachHangRemoteDataSource.getBankInfo(request);
    }

    @Override
    public Observable<GetCurrBillCycleResponse> getCurrBillCycle(
            DataRequest<GetCurrBillCycleRequest> request) {
        return qLKhachHangRemoteDataSource.getCurrBillCycle(request);
    }
}
