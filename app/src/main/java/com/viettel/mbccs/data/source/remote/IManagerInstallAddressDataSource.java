package com.viettel.mbccs.data.source.remote;

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

public interface IManagerInstallAddressDataSource {

    Observable<GetListTeamResponse> getListTeam(
            DataRequest<GetListTeamRequest> request);


    Observable<GetListDsLamByTeamIdResponse> getListDsLamByTeamId(
            DataRequest<GetListDsLamByTeamIdRequest> request);

    Observable<GetReceiverChangeAddressResponse> receiverChangeAddress(
            AddressRequest<GetReceiverChangeAddressRequest> request);
}
