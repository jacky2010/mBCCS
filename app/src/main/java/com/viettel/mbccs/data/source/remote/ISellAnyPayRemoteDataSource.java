package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.SaleAnypayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.SaleAnypayResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ISellAnyPayRemoteDataSource {
    Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request);
    Observable<SaleAnypayResponse> saleAnypay(DataRequest<SaleAnypayRequest> request);
}
