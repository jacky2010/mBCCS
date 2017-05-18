package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserRemoteDataSource {

    Observable<LoginResponse> login(LoginRequest loginRequest);
    Observable<Object> sendCodeChangePass(String phone);
}
