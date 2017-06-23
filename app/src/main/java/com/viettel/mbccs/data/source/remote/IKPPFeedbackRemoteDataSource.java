package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.IsKPPManagerRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPRespondFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPRespondFeedbackResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IKPPFeedbackRemoteDataSource {
    Observable<GetKPPFeedbackResponse> getFeedback(DataRequest<GetKPPFeedbackRequest> request);

    Observable<GetKPPFeedbackInfoResponse> getFeedbackInfo(DataRequest<GetKPPFeedbackInfoRequest> request);

    Observable<KPPFeedbackResponse> feedback(DataRequest<KPPFeedbackRequest> request);

    Observable<KPPRespondFeedbackResponse> responseFeedback(DataRequest<KPPRespondFeedbackRequest> request);

    Observable<DataResponse> isKPPManager(DataRequest<IsKPPManagerRequest> request);
}
