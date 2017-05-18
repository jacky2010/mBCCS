package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface MBCSSApi {

    @POST("/login")
    Observable<BaseResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("/send_code_password")
    Observable<BaseResponse<Object>> sendCodeChangePass(@Field("phone") String phone);

    @GET("/login")
    Observable<BaseResponse<List<SaleOrders>>> searchSellOrders(
            @Body BaseRequest<GetListOrderRequest> request);

    @GET("/login")
    Observable<BaseResponse<List<ChannelInfo>>> getListChannelByOwnerTypeId(
            @Body BaseRequest<GetListChannelByOwnerTypeIdRequest> request);
}
