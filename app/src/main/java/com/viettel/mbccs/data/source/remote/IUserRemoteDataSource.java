package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserRemoteDataSource {

    Observable<LoginInfo> login(LoginRequest loginRequest);

    Observable<Object> sendCodeChangePass(String phone);

    Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            BaseRequest<GetTelecomServiceAndSaleProgramRequest> request);

    Observable<GetSerialsReponse> getSerial(BaseRequest<GetSerialRequest> request);

    Observable<List<ModelSale>> getModelSales(BaseRequest<GetTotalStockRequest> request);

    Observable<SaleTrans> getSaleTransInfo(BaseRequest<GetInfoSaleTranRequest> request);

    Observable<SaleTrans> createSaleTransRetail(BaseRequest<GetInfoSaleTranRequest> request);


}
