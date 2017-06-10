package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ISurveyKPPRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SurveyKPPRemoteDataSource implements ISurveyKPPRemoteDataSource {

    public volatile static SurveyKPPRemoteDataSource instance;

    public SurveyKPPRemoteDataSource() {

    }

    @Override
    public Observable<GetSurveyKPPResponse> getSurveyKPP(DataRequest<GetSurveyKPPRequest> request) {
        return RequestHelper.getRequest()
                .getSurveyKPP(request)
                .flatMap(SchedulerUtils.<GetSurveyKPPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetSurveyKPPResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<SendSurveyKPPResponse> sendSurveyKPP(DataRequest<SendSurveyKPPRequest> request) {
        return RequestHelper.getRequest()
                .sendSurveyKPP(request)
                .flatMap(SchedulerUtils.<SendSurveyKPPResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SendSurveyKPPResponse>applyAsyncSchedulers());
    }

    public static SurveyKPPRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new SurveyKPPRemoteDataSource();
        }
        return instance;
    }
}
