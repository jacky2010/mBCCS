package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IApDomainLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.ApDomainLocalDataSource;
import com.viettel.mbccs.data.source.remote.IApDomainRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.ApDomainRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ApDomainRepository implements IApDomainLocalDataSource, IApDomainRemoteDataSource {

    private volatile static ApDomainRepository instance;
    private ApDomainLocalDataSource localDataSource;
    private ApDomainRemoteDataSource remoteDataSource;

    public ApDomainRepository(ApDomainLocalDataSource localDataSource,
                              ApDomainRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static ApDomainRepository getInstance() {
        if (instance == null) {
            instance = new ApDomainRepository(ApDomainLocalDataSource.getInstance(),
                    ApDomainRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetApDomainByTypeResponse> getApDomainByType(DataRequest<GetApDomainByTypeRequest> request) {
        return remoteDataSource.getApDomainByType(request);
    }
}
