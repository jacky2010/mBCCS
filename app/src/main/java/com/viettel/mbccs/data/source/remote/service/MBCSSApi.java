package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface MBCSSApi {

    @POST("/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);
}
