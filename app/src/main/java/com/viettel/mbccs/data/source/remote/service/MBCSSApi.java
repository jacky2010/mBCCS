package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.request.AddBranchRequest;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.SearchBranchRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import com.viettel.mbccs.data.source.remote.response.OrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;

import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
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

    @POST("/login")
    Observable<BaseResponse<List<SaleOrders>>> searchSellOrders(
            @Body BaseRequest<GetListOrderRequest> request);

    @POST("/login")
    Observable<BaseResponse<List<ChannelInfo>>> getListChannelByOwnerTypeId(
            @Body BaseRequest<GetListChannelByOwnerTypeIdRequest> request);

    @POST("/telecomservice_salproram")
    Observable<BaseResponse<TelecomServiceAndSaleProgramResponse>> getTelecomserviceAndSaleProgram(
            @Body BaseRequest<GetTelecomServiceAndSaleProgramRequest> request);

    @POST("/getserials")
    Observable<BaseResponse<GetSerialsReponse>> getSerials(
            @Body BaseRequest<GetSerialRequest> request);

    @GET("/login")
    Observable<BaseResponse<OrderInfoResponse>> getOrderInfo(
            @Body BaseRequest<GetOrderInfoRequest> request);

    @POST("/getsalemodel")
    Observable<BaseResponse<List<ModelSale>>> getModelSales(
            @Body BaseRequest<GetTotalStockRequest> request);

    @POST("/getinfortrans")
    Observable<BaseResponse<SaleTrans>> getSaleTransInfo(
            @Body BaseRequest<GetInfoSaleTranRequest> request);

    @POST("/savetransaction")
    Observable<BaseResponse<SaleTrans>> createSaleTransRetail(
            @Body BaseRequest<GetInfoSaleTranRequest> request);

    @POST("/getDistributtedChannelInfo")
    Observable<BaseResponse<BranchItem>> getDistributtedChannelInfo(
            @Body SearchBranchRequest request);

    @POST("/createDistributtedChannel")
    Observable<BaseResponse<BranchItem>> createDistributtedChannel(
            @Body AddBranchRequest request);
    @POST("/getliststockmodel")
    Observable<BaseResponse<List<StockTotal>>> getListStockModel(
            @Body BaseRequest<GetListStockModelRequest> request);

    @POST("/viewinfoserial")
    Observable<BaseResponse<List<StockSerial>>> viewInfoSerial(
            @Body BaseRequest<ViewInfoSerialRequest> request);

    @POST("/getListProvince")
    Observable<BaseResponse<List<Area>>> getListProvince(
            @Body BaseRequest<GetListProvinceRequest> request);

    @POST("/getListTTKD")
    Observable<BaseResponse<List<Shop>>> getListTTKD(
            @Body BaseRequest<GetListTTKDRequest> request);

    @POST("/getListTTKD")
    Observable<BaseResponse<List<Shop>>> getListShop(
            @Body BaseRequest<GetListShopRequest> request);
}
