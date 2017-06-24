package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.remote.datasource.ManagerInstallRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.AddressRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListDsLamByTeamIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTeamRequest;
import com.viettel.mbccs.data.source.remote.request.GetReceiverChangeAddressRequest;
import com.viettel.mbccs.data.source.remote.response.GetListDsLamByTeamIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTeamResponse;
import com.viettel.mbccs.data.source.remote.response.GetReceiverChangeAddressResponse;

import rx.Observable;

/**
 * Created by jacky on 6/23/17.
 */

public class ManagerInstallAddressRepository {

    private volatile static ManagerInstallAddressRepository instance;
    private ManagerInstallRemoteDataSource mManagerInstallRemoteDataSource;

    public ManagerInstallAddressRepository(ManagerInstallRemoteDataSource mManagerInstallRemoteDataSource) {
        this.mManagerInstallRemoteDataSource = mManagerInstallRemoteDataSource;
    }

    public static ManagerInstallAddressRepository getInstance() {
        if (instance == null) {
            instance = new ManagerInstallAddressRepository(ManagerInstallRemoteDataSource.getInstance());
        }
        return instance;
    }

    public Observable<GetListTeamResponse> getListTeam(DataRequest<GetListTeamRequest> request) {
        return mManagerInstallRemoteDataSource.getListTeam(request);
    }

    public Observable<GetListDsLamByTeamIdResponse> getListDsLamByTeamId(DataRequest<GetListDsLamByTeamIdRequest> request) {
        return mManagerInstallRemoteDataSource.getListDsLamByTeamId(request);
    }


    public Observable<GetReceiverChangeAddressResponse> receiverChangeAddress(AddressRequest<GetReceiverChangeAddressRequest> request){
        return mManagerInstallRemoteDataSource.receiverChangeAddress(request);
    }
}
