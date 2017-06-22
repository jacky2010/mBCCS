package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IKPPFeedbackLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.KPPFeedbackLocalDataSource;
import com.viettel.mbccs.data.source.remote.IKPPFeedbackRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.KPPFeedbackRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPRespondFeedbackRequest;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPRespondFeedbackResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class KPPFeedbackRepository implements IKPPFeedbackLocalDataSource, IKPPFeedbackRemoteDataSource {

    private volatile static KPPFeedbackRepository instance;
    private KPPFeedbackLocalDataSource localDataSource;
    private KPPFeedbackRemoteDataSource remoteDataSource;

    public KPPFeedbackRepository(KPPFeedbackLocalDataSource localDataSource,
                                 KPPFeedbackRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static KPPFeedbackRepository getInstance() {
        if (instance == null) {
            instance = new KPPFeedbackRepository(KPPFeedbackLocalDataSource.getInstance(),
                    KPPFeedbackRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetKPPFeedbackResponse> getFeedback(DataRequest<GetKPPFeedbackRequest> request) {
        return remoteDataSource.getFeedback(request);
    }

    @Override
    public Observable<GetKPPFeedbackInfoResponse> getFeedbackInfo(DataRequest<GetKPPFeedbackInfoRequest> request) {
        return remoteDataSource.getFeedbackInfo(request);
    }

    @Override
    public Observable<KPPFeedbackResponse> feedback(DataRequest<KPPFeedbackRequest> request) {
        return remoteDataSource.feedback(request);
    }

    @Override
    public Observable<KPPRespondFeedbackResponse> responseFeedback(DataRequest<KPPRespondFeedbackRequest> request) {
        return remoteDataSource.responseFeedback(request);
    }
}
