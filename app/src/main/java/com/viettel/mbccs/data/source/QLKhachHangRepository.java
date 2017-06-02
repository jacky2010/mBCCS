package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.datasource.QLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.local.IQLKhachHangLocalDataSource;
import com.viettel.mbccs.data.source.remote.datasource.QLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.ChecOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
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
    public Observable<GetRegiterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegiterSubInfoRequest> request) {
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
    public Observable<GetApDomainResponse> getApDomain(DataRequest<GetApDomainRequest> request) {
        return qLKhachHangRemoteDataSource.getApDomain(request);
    }

    @Override
    public Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request) {
        return qLKhachHangRemoteDataSource.getOTP(request);
    }

    @Override
    public Observable<ChecOTPResponse> checOTP(DataRequest<ChecOTPRequest> request) {
        return qLKhachHangRemoteDataSource.checOTP(request);
    }

    @Override
    public Observable<UpdateAllSubInfoResponse> updateAllSubInfo(
            DataRequest<UpdateAllSubInfoRequest> request) {
        return qLKhachHangRemoteDataSource.updateAllSubInfo(request);
    }
}
