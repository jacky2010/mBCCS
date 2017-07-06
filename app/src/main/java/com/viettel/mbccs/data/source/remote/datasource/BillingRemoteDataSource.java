package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.remote.IBillingDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSearchTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetSaleTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetCreateInvoiceBillRequest;
import com.viettel.mbccs.data.source.remote.response.GetListSearchTransResponse;
import com.viettel.mbccs.data.source.remote.response.GetSaleTransDetailResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by jacky on 7/3/17.
 */

public class BillingRemoteDataSource implements IBillingDataSource {

    public volatile static BillingRemoteDataSource instance;

    public BillingRemoteDataSource() {

    }

    public static BillingRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BillingRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetListSearchTransResponse> getListSearchTrans(DataRequest<GetListSearchTransRequest> request) {
        return  RequestHelper.getRequest()
                .getListSearchTrans(request)
                .flatMap(SchedulerUtils.<GetListSearchTransResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListSearchTransResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetSaleTransDetailResponse> getSaleTransDetail(DataRequest<GetSaleTransDetailRequest> request) {
        return  RequestHelper.getRequest()
                .getSaleTransDetail(request)
                .flatMap(SchedulerUtils.<GetSaleTransDetailResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetSaleTransDetailResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<EmptyObject> createInvoiceBill(DataRequest<GetCreateInvoiceBillRequest> request) {
        return  RequestHelper.getRequest()
                .createInvoiceBill(request)
                .flatMap(SchedulerUtils.<EmptyObject>convertDataFlatMap())
                .compose(SchedulerUtils.<EmptyObject>applyAsyncSchedulers());
    }

}
