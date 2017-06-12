package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IHotNewsCSKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class HotNewsCSKPPRemoteDataSource implements IHotNewsCSKPPRemoteDataSource {

    public volatile static HotNewsCSKPPRemoteDataSource instance;

    public HotNewsCSKPPRemoteDataSource() {

    }

    @Override
    public Observable<GetHotNewsCSKPPResponse> getHotNews(DataRequest<GetHotNewsCSKPPRequest> request) {
        return RequestHelper.getRequest()
                .getHotNews(request)
                .flatMap(SchedulerUtils.<GetHotNewsCSKPPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetHotNewsCSKPPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetHotNewsInfoCSKPPResponse> getHotNewsInfo(DataRequest<GetHotNewsInfoCSKPPRequest> request) {
        return RequestHelper.getRequest()
                .getHotNewsInfo(request)
                .flatMap(SchedulerUtils.<GetHotNewsInfoCSKPPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetHotNewsInfoCSKPPResponse>applyAsyncSchedulers());
    }

    public static HotNewsCSKPPRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new HotNewsCSKPPRemoteDataSource();
        }
        return instance;
    }
}
