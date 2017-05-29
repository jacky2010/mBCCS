package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.source.remote.IBranchesRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.AddBranchRequest;
import com.viettel.mbccs.data.source.remote.request.SearchBranchKeyRequest;
import com.viettel.mbccs.data.source.remote.request.SearchBranchRequest;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BranchesRemoteDataSource implements IBranchesRemoteDataSource {

    public volatile static BranchesRemoteDataSource instance;

    public BranchesRemoteDataSource() {

    }

    public static BranchesRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BranchesRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetDistributedChannelResponse> getDistributtedChannelInfo(String isdn, String documentId) {

        SearchBranchKeyRequest key = new SearchBranchKeyRequest();
        key.setIsdn(isdn);
        key.setDocumentId(documentId);

        SearchBranchRequest request = new SearchBranchRequest();
        request.setObjectBO(key);

        return RequestHelper.getRequest()
                .getDistributtedChannelInfo(request)
                .flatMap(SchedulerUtils.<GetDistributedChannelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetDistributedChannelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CreateDistributedChannelResponse> createDistributtedChannel(BranchItem item) {

        AddBranchRequest request = new AddBranchRequest();
        request.setObjectBO(item);

        return RequestHelper.getRequest()
                .createDistributtedChannel(request)
                .flatMap(SchedulerUtils.<CreateDistributedChannelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateDistributedChannelResponse>applyAsyncSchedulers());
    }
}
