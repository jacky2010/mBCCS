package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IApDomainRemoteDataSource {
    Observable<GetApDomainByTypeResponse> getApDomainByType(DataRequest<GetApDomainByTypeRequest> request);
}
