package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserRemoteDataSource {

    Observable<LoginResponse> login(LoginRequest loginRequest);

    Observable<Object> sendCodeChangePass(String phone);

    Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            BaseRequest<GetTelecomServiceAndSaleProgramRequest> request);

    Observable<GetSerialsReponse> getSerial(BaseRequest<GetSerialRequest> request);
}
