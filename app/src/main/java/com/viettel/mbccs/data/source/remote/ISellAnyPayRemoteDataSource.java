package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToChannelRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToCustomerRequest;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToChannelResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToCustomerResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ISellAnyPayRemoteDataSource {
    Observable<GetAnypayAuthorizeResponse> getAnypayAuthorize(DataRequest<GetAnypayAuthorizeRequest> request);
    Observable<SellAnypayToCustomerResponse> sellAnypayToCustomer(DataRequest<SellAnypayToCustomerRequest> request);
    Observable<SellAnypayToChannelResponse> sellAnypayToChannel(DataRequest<SellAnypayToChannelRequest> request);
}
