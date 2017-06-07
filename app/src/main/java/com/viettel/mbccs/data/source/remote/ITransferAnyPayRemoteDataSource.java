package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.PayTransferAnypayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.PayTransferAnypayResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ITransferAnyPayRemoteDataSource {
    Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request);
    Observable<PayTransferAnypayResponse> payTransferAnypay(DataRequest<PayTransferAnypayRequest> request);
}
