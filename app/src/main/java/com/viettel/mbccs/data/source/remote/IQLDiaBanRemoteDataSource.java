package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;

import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public interface IQLDiaBanRemoteDataSource {

    Observable<GetListProvinceResponse> getListProvince(
            DataRequest<GetListProvinceRequest> request);
}
