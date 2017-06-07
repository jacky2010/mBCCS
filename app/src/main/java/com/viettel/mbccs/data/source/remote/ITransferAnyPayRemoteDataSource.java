package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.RefillAnyPayRequest;
import com.viettel.mbccs.data.source.remote.request.TransferAnyPayRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.RefillAnyPayResponse;
import com.viettel.mbccs.data.source.remote.response.TransferAnyPayResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ITransferAnyPayRemoteDataSource {
    Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request);
    Observable<TransferAnyPayResponse> transferAnyPay(DataRequest<TransferAnyPayRequest> request);
    Observable<RefillAnyPayResponse> refillAnyPay(DataRequest<RefillAnyPayRequest> request);
}
