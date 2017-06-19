package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.CreateDistributedChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.GetUserInfoRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.KPPResponseFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.RefillAnyPayRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.SearchBranchRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToChannelRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToCustomerRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.TransferAnyPayRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.request.UploadImageRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPResponseFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.RefillAnyPayResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToChannelResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToCustomerResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.ServerDataResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.TransferAnyPayResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface MBCSSApi {

    @POST("/JsonAPI/webresources/CoreService/login")
    Observable<LoginInfo> login(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SendCodeChangePassResponse>>> sendCodeChangePass(
            @Field("phone") String phone);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListOrderResponse>>> getListOrder(
            @Body DataRequest<GetListOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListChannelByOwnerTypeIdResponse>>> getListChannelByOwnerTypeId(
            @Body DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetAllInfoResponse>>> WS_GetAllInfo(
            @Body DataRequest<GetAllInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<TelecomServiceAndSaleProgramResponse>>> getTelecomserviceAndSaleProgram(
            @Body DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetSerialsResponse>>> getSerials(
            @Body DataRequest<GetSerialRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetOrderInfoResponse>>> getOrderInfo(
            @Body DataRequest<GetOrderInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetReasonResponse>>> getReason(
            @Body DataRequest<GetReasonRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransFromOrderResponse>>> createSaleTransFromOrder(
            @Body DataRequest<CreateSaleTransFromOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UpdateSaleOrderResponse>>> updateSaleOrder(
            @Body DataRequest<UpdateSaleOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetTotalStockResponse>>> getModelSales(
            @Body DataRequest<GetTotalStockRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetInfoSaleTranResponse>>> getSaleTransInfo(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransRetailResponse>>> createSaleTransRetail(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetDistributedChannelResponse>>> getDistributtedChannelInfo(
            @Body SearchBranchRequest request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateDistributedChannelResponse>>> createDistributtedChannel(
            @Body DataRequest<CreateDistributedChannelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransChannelResponse>>> createSaleTransChannel(
            @Body DataRequest<CreateSaleTransChannelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListStockModelResponse>>> getListStockModel(
            @Body DataRequest<GetListStockModelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListSerialResponse>>> getListSerial(
            @Body DataRequest<GetListSerialRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListProvinceResponse>>> getListProvince(
            @Body DataRequest<GetListProvinceRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListTTKDResponse>>> getListTTKD(
            @Body DataRequest<GetListTTKDRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListShopResponse>>> getListShop(
            @Body DataRequest<GetListShopRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createSaleOrders(
            @Body DataRequest<KPPOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<RegisterCustomerInfoResponse>>> registerCustomerInfo(
            @Body DataRequest<RegisterCustomerInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetRegiterSubInfoResponse>>> getRegiterSubInfo(
            @Body DataRequest<GetRegiterSubInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListBusTypeIdRequireResponse>>> getListBusTypeIdRequire(
            @Body DataRequest<GetListBusTypeIdRequireRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetApDomainResponse>>> getApDomain(
            @Body DataRequest<GetApDomainRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetOTPResponse>>> getOTP(
            @Body DataRequest<GetOTPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CheckOTPResponse>>> checOTP(
            @Body DataRequest<ChecOTPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UpdateAllSubInfoResponse>>> updateAllSubInfo(
            @Body DataRequest<UpdateAllSubInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetProvinceResponse>>> getProvince(
            @Body DataRequest<GetProvinceRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetDistrictResponse>>> getDistrict(
            @Body DataRequest<GetDistrictRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetPrecinctResponse>>> getPrecinct(
            @Body DataRequest<GetPrecinctRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CheckIdNoResponse>>> checkIdNo(
            @Body DataRequest<CheckIdNoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetAnypayAuthorizeResponse>>> getAnypayAuthorize(
            @Body DataRequest<GetAnypayAuthorizeRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SellAnypayToCustomerResponse>>> saleAnypayToCustomer(
            @Body DataRequest<SellAnypayToCustomerRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SellAnypayToChannelResponse>>> saleAnypayToChannel(
            @Body DataRequest<SellAnypayToChannelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<TransferAnyPayResponse>>> transferAnyPay(
            @Body DataRequest<TransferAnyPayRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<RefillAnyPayResponse>>> refillAnyPay(
            @Body DataRequest<RefillAnyPayRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> checkChangeSimDebit(
            @Body DataRequest<CheckDebitChangeSimRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> checkCalledIsdnChangeSim(
            @Body DataRequest<CheckCalledIsdnsRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> changeSim(
            @Body DataRequest<ChangeSimRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetSurveyKPPResponse>>> getSurveyKPP(
            @Body DataRequest<GetSurveyKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SendSurveyKPPResponse>>> sendSurveyKPP(
            @Body DataRequest<SendSurveyKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetHotNewsCSKPPResponse>>> getHotNews(
            @Body DataRequest<GetHotNewsCSKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetHotNewsInfoCSKPPResponse>>> getHotNewsInfo(
            @Body DataRequest<GetHotNewsInfoCSKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetKPPFeedbackResponse>>> getKPPFeedback(
            @Body DataRequest<GetKPPFeedbackRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetKPPFeedbackInfoResponse>>> getKPPFeedbackInfo(
            @Body DataRequest<GetKPPFeedbackInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<KPPFeedbackResponse>>> kppFeedback(
            @Body DataRequest<KPPFeedbackRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<KPPResponseFeedbackResponse>>> kppResponseFeedback(
            @Body DataRequest<KPPResponseFeedbackRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> uploadImage(
            @Body DataRequest<UploadImageRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UserInfo>>> getUserInfo(
            @Body DataRequest<GetUserInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DownloadImageResponse>>> downloadImage(
            @Body DataRequest<DownloadImageRequest> request);
}
