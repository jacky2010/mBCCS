package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IManagerInstallAddressDataSource;
import com.viettel.mbccs.data.source.remote.request.AddressRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListDsLamByTeamIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTeamRequest;
import com.viettel.mbccs.data.source.remote.request.GetReceiverChangeAddressRequest;
import com.viettel.mbccs.data.source.remote.response.GetListDsLamByTeamIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTeamResponse;
import com.viettel.mbccs.data.source.remote.response.GetReceiverChangeAddressResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by jacky on 6/23/17.
 */

public class ManagerInstallRemoteDataSource implements IManagerInstallAddressDataSource {

    public volatile static ManagerInstallRemoteDataSource instance;

    public ManagerInstallRemoteDataSource() {

    }

    public static ManagerInstallRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ManagerInstallRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetListTeamResponse> getListTeam(DataRequest<GetListTeamRequest> request) {
        return RequestHelper.getRequest()
                .getListTeam(request)
                .flatMap(SchedulerUtils.<GetListTeamResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListTeamResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListDsLamByTeamIdResponse> getListDsLamByTeamId(DataRequest<GetListDsLamByTeamIdRequest> request) {
        return RequestHelper.getRequest()
                .getListDsLamByTeamId(request)
                .flatMap(SchedulerUtils.<GetListDsLamByTeamIdResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListDsLamByTeamIdResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetReceiverChangeAddressResponse> receiverChangeAddress(AddressRequest<GetReceiverChangeAddressRequest> request) {
        return RequestHelper.getRequest()
                .receiverChangeAddress(request)
                .flatMap(SchedulerUtils.<GetReceiverChangeAddressResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetReceiverChangeAddressResponse>applyAsyncSchedulers());
    }
}
