package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.CheckCalledIsdnResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateRegisterSubResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IChangeSimRemoteDataSource {
    Observable<GetRegisterSubResponse> getRegisterSub(DataRequest<GetRegisterSubRequest> request);

    Observable<CheckCalledIsdnResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnRequest> request);

    Observable<UpdateRegisterSubResponse> updateRegisterSub(DataRequest<UpdateRegisterSubRequest> request);
}
