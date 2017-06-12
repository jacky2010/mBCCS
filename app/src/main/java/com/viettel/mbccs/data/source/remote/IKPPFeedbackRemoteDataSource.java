package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPResponseFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPResponseFeedbackResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IKPPFeedbackRemoteDataSource {
    Observable<GetKPPFeedbackResponse> getFeedback(DataRequest<GetKPPFeedbackRequest> request);

    Observable<GetKPPFeedbackInfoResponse> getFeedbackInfo(DataRequest<GetKPPFeedbackInfoRequest> request);

    Observable<KPPFeedbackResponse> feedback(DataRequest<KPPFeedbackRequest> request);

    Observable<KPPResponseFeedbackResponse> responseFeedback(DataRequest<KPPResponseFeedbackRequest> request);
}
