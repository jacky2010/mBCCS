package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.remote.datasource.BillingRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListApParamsRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSearchTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetSaleTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetCreateInvoiceBillRequest;
import com.viettel.mbccs.data.source.remote.response.GetListApParamsResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSearchTransResponse;
import com.viettel.mbccs.data.source.remote.response.GetSaleTransDetailResponse;

import rx.Observable;

/**
 * Created by jacky on 7/3/17.
 */

public class BillingRepository {

    private volatile static BillingRepository instance;
    private BillingRemoteDataSource mBillingRemoteDataSource;

    public BillingRepository(BillingRemoteDataSource mBillingRemoteDataSource) {
        this.mBillingRemoteDataSource = mBillingRemoteDataSource;
    }

    public static BillingRepository getInstance() {
        if (instance == null) {
            instance = new BillingRepository(BillingRemoteDataSource.getInstance());
        }
        return instance;
    }

    public Observable<GetListSearchTransResponse> getListSearchTrans(DataRequest<GetListSearchTransRequest> request) {
        return mBillingRemoteDataSource.getListSearchTrans(request);
    }

    public Observable<GetSaleTransDetailResponse> getSaleTransDetail(DataRequest<GetSaleTransDetailRequest> request) {
        return mBillingRemoteDataSource.getSaleTransDetail(request);
    }

    public Observable<EmptyObject> createInvoiceBill(DataRequest<GetCreateInvoiceBillRequest> request) {
        return mBillingRemoteDataSource.createInvoiceBill(request);
    }

    public Observable<GetListApParamsResponse> getApParam(DataRequest<GetListApParamsRequest> request) {
        return mBillingRemoteDataSource.getApParam(request);
    }
}
