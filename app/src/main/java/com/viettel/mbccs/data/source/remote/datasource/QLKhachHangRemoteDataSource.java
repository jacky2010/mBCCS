package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IQLKhachHangRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
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
}
