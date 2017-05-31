package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ViewInfoSerialResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface MBCSSApi {

    @POST("/login")
    Observable<LoginInfo> login(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("/send_code_password")
    Observable<BaseResponse<SendCodeChangePassResponse>> sendCodeChangePass(
            @Field("phone") String phone);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetListOrder")
    Observable<BaseResponse<GetListOrderResponse>> getListOrder(
            @Body DataRequest<GetListOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_getListChannelByOwnerTypeId")
    Observable<BaseResponse<GetListChannelByOwnerTypeIdResponse>> getListChannelByOwnerTypeId(
            @Body DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetAllInfo")
    Observable<BaseResponse<GetAllInfoResponse>> WS_GetAllInfo(
            @Body DataRequest<GetAllInfoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetTelecomServiceAndSaleProgram")
    Observable<BaseResponse<TelecomServiceAndSaleProgramResponse>> getTelecomserviceAndSaleProgram(
            @Body DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetListSerial")
    Observable<BaseResponse<GetSerialsResponse>> getSerials(
            @Body DataRequest<GetSerialRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetOrderInfo")
    Observable<BaseResponse<GetOrderInfoResponse>> getOrderInfo(
            @Body DataRequest<GetOrderInfoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetReason")
    Observable<BaseResponse<GetReasonResponse>> getReason(
            @Body DataRequest<GetReasonRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransFromOrder")
    Observable<BaseResponse<CreateSaleTransFromOrderResponse>> createSaleTransFromOrder(
            @Body DataRequest<CreateSaleTransFromOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_UpdateSaleOrder")
    Observable<BaseResponse<UpdateSaleOrderResponse>> updateSaleOrder(
            @Body DataRequest<UpdateSaleOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetStockTotal")
    Observable<BaseResponse<GetTotalStockResponse>> getModelSales(
            @Body DataRequest<GetTotalStockRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_getSaleTransInfo")
    Observable<BaseResponse<GetInfoSaleTranResponse>> getSaleTransInfo(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransRetail")
    Observable<BaseResponse<CreateSaleTransRetailResponse>> createSaleTransRetail(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransChannel")
    Observable<BaseResponse<CreateSaleTransChannelResponse>> createSaleTransChannel(
            @Body DataRequest<CreateSaleTransChannelRequest> request);

    @POST("/getliststockmodel")
    Observable<BaseResponse<GetListStockModelResponse>> getListStockModel(
            @Body DataRequest<GetListStockModelRequest> request);

    @POST("/viewinfoserial")
    Observable<BaseResponse<ViewInfoSerialResponse>> viewInfoSerial(
            @Body DataRequest<ViewInfoSerialRequest> request);

    @POST("/getListProvince")
    Observable<BaseResponse<GetListProvinceResponse>> getListProvince(
            @Body DataRequest<GetListProvinceRequest> request);

    @POST("/getListTTKD")
    Observable<BaseResponse<GetListTTKDResponse>> getListTTKD(
            @Body DataRequest<GetListTTKDRequest> request);

    @POST("/getListTTKD")
    Observable<BaseResponse<GetListShopResponse>> getListShop(
            @Body DataRequest<GetListShopRequest> request);

    @POST("/createSaleOrder")
    Observable<BaseResponse> createSaleOrders(
            @Body DataRequest<KPPOrderRequest> requestBaseResponse);
}
