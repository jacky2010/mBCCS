package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IHotNewsCSKPPRemoteDataSource {
    Observable<GetHotNewsCSKPPResponse> getHotNews(DataRequest<GetHotNewsCSKPPRequest> request);

    Observable<GetHotNewsInfoCSKPPResponse> getHotNewsInfo(DataRequest<GetHotNewsInfoCSKPPRequest> request);
}
