package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserRemoteDataSource {

    Observable<LoginInfo> login(LoginRequest loginRequest);

    Observable<SendCodeChangePassResponse> sendCodeChangePass(String phone);

    Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request);

    Observable<GetTotalStockResponse> getModelSales(DataRequest<GetTotalStockRequest> request);

    Observable<GetInfoSaleTranResponse> getSaleTransInfo(DataRequest<GetInfoSaleTranRequest> request);

    Observable<GetInfoSaleTranResponse> createSaleTransRetail(DataRequest<GetInfoSaleTranRequest> request);


}
