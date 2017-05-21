package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.BranchItem;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IBranchesRemoteDataSource {

    Observable<BranchItem> getDistributtedChannelInfo(String isdn, String documentId);

    Observable<BranchItem> createDistributtedChannel(BranchItem request);
}
