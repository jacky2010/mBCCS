package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ISurveyKPPRemoteDataSource {
    Observable<GetSurveyKPPResponse> getSurveyKPP(DataRequest<GetSurveyKPPRequest> request);
    Observable<SendSurveyKPPResponse> sendSurveyKPP(DataRequest<SendSurveyKPPRequest> request);
}
