package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IKPPFeedbackRemoteDataSource;
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
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class KPPFeedbackRemoteDataSource implements IKPPFeedbackRemoteDataSource {

    public volatile static KPPFeedbackRemoteDataSource instance;

    public KPPFeedbackRemoteDataSource() {

    }

    @Override
    public Observable<GetKPPFeedbackResponse> getFeedback(DataRequest<GetKPPFeedbackRequest> request) {
        return RequestHelper.getRequest()
                .getKPPFeedback(request)
                .flatMap(SchedulerUtils.<GetKPPFeedbackResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetKPPFeedbackResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetKPPFeedbackInfoResponse> getFeedbackInfo(DataRequest<GetKPPFeedbackInfoRequest> request) {
        return RequestHelper.getRequest()
                .getKPPFeedbackInfo(request)
                .flatMap(SchedulerUtils.<GetKPPFeedbackInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetKPPFeedbackInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<KPPFeedbackResponse> feedback(DataRequest<KPPFeedbackRequest> request) {
        return RequestHelper.getRequest()
                .kppFeedback(request)
                .flatMap(SchedulerUtils.<KPPFeedbackResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<KPPFeedbackResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<KPPRespondFeedbackResponse> responseFeedback(DataRequest<KPPRespondFeedbackRequest> request) {
        return RequestHelper.getRequest()
                .kppResponseFeedback(request)
                .flatMap(SchedulerUtils.<KPPRespondFeedbackResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<KPPRespondFeedbackResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<DataResponse> isKPPManager(DataRequest<IsKPPManagerRequest> request) {
        return RequestHelper.getRequest()
                .isKppManager(request)
                .flatMap(SchedulerUtils.<DataResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<DataResponse>applyAsyncSchedulers());
    }

    public static KPPFeedbackRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new KPPFeedbackRemoteDataSource();
        }
        return instance;
    }
}
