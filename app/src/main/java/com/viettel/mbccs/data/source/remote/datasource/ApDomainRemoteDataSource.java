package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IApDomainRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ApDomainRemoteDataSource implements IApDomainRemoteDataSource {

    public volatile static ApDomainRemoteDataSource instance;

    public ApDomainRemoteDataSource() {

    }

    @Override
    public Observable<GetApDomainByTypeResponse> getApDomainByType(DataRequest<GetApDomainByTypeRequest> request) {
        return RequestHelper.getRequest()
                .getApDomainByType(request)
                .flatMap(SchedulerUtils.<GetApDomainByTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetApDomainByTypeResponse>applyAsyncSchedulers());
    }

    public static ApDomainRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ApDomainRemoteDataSource();
        }
        return instance;
    }
}
