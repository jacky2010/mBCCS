package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.ISurveyKPPLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.SurveyKPPLocalDataSource;
import com.viettel.mbccs.data.source.remote.ISurveyKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.SurveyKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SurveyKPPRepository implements ISurveyKPPLocalDataSource, ISurveyKPPRemoteDataSource {

    private volatile static SurveyKPPRepository instance;
    private SurveyKPPLocalDataSource localDataSource;
    private SurveyKPPRemoteDataSource remoteDataSource;

    public SurveyKPPRepository(SurveyKPPLocalDataSource localDataSource,
                               SurveyKPPRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SurveyKPPRepository getInstance() {
        if (instance == null) {
            instance = new SurveyKPPRepository(SurveyKPPLocalDataSource.getInstance(),
                    SurveyKPPRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetSurveyKPPResponse> getSurveyKPP(DataRequest<GetSurveyKPPRequest> request) {
        return remoteDataSource.getSurveyKPP(request);
    }

    @Override
    public Observable<SendSurveyKPPResponse> sendSurveyKPP(DataRequest<SendSurveyKPPRequest> request) {
        return remoteDataSource.sendSurveyKPP(request);
    }
}
