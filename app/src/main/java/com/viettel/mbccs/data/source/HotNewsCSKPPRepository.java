package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IHotNewsCSKPPLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.HotNewsCSKPPLocalDataSource;
import com.viettel.mbccs.data.source.remote.IHotNewsCSKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.HotNewsCSKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class HotNewsCSKPPRepository implements IHotNewsCSKPPLocalDataSource, IHotNewsCSKPPRemoteDataSource {

    private volatile static HotNewsCSKPPRepository instance;
    private HotNewsCSKPPLocalDataSource localDataSource;
    private HotNewsCSKPPRemoteDataSource remoteDataSource;

    public HotNewsCSKPPRepository(HotNewsCSKPPLocalDataSource localDataSource,
                                  HotNewsCSKPPRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static HotNewsCSKPPRepository getInstance() {
        if (instance == null) {
            instance = new HotNewsCSKPPRepository(HotNewsCSKPPLocalDataSource.getInstance(),
                    HotNewsCSKPPRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetHotNewsCSKPPResponse> getHotNews(DataRequest<GetHotNewsCSKPPRequest> request) {
        return remoteDataSource.getHotNews(request);
    }

    @Override
    public Observable<GetHotNewsInfoCSKPPResponse> getHotNewsInfo(DataRequest<GetHotNewsInfoCSKPPRequest> request) {
        return remoteDataSource.getHotNewsInfo(request);
    }
}
