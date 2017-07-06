package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSearchTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetSaleTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetCreateInvoiceBillRequest;
import com.viettel.mbccs.data.source.remote.response.GetListSearchTransResponse;
import com.viettel.mbccs.data.source.remote.response.GetSaleTransDetailResponse;

import rx.Observable;

/**
 * Created by jacky on 7/3/17.
 */

public interface IBillingDataSource {

    Observable<GetListSearchTransResponse> getListSearchTrans(
            DataRequest<GetListSearchTransRequest> request);

    Observable<GetSaleTransDetailResponse> getSaleTransDetail(
            DataRequest<GetSaleTransDetailRequest> request);

    Observable<EmptyObject> createInvoiceBill(
            DataRequest<GetCreateInvoiceBillRequest> request);
}
