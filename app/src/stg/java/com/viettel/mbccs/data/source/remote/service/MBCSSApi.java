package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.request.AddressRequest;
import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.CreateDistributedChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotHaveCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetCreateInvoiceBillRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListDsLamByTeamIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListIdImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListRegTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSearchTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSubTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTeamRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.GetReceiverChangeAddressRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.request.GetSaleTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.GetUserInfoRequest;
import com.viettel.mbccs.data.source.remote.request.InputOrderRequest;
import com.viettel.mbccs.data.source.remote.request.IsKPPManagerRequest;
import com.viettel.mbccs.data.source.remote.request.KPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.KPPRespondFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.PassResetRequest;
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
import com.viettel.mbccs.data.source.remote.request.ViewInforSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNote;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.ConnectSubscriberResponse;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListDsLamByTeamIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListRegTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSearchTransResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSubTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTeamResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetReceiverChangeAddressResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.data.source.remote.response.GetSaleTransDetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetSurveyKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.InputOrderResponse;
import com.viettel.mbccs.data.source.remote.response.KPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.KPPRespondFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
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
import com.viettel.mbccs.data.source.remote.response.ViewInforSerialResponse;
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

    @POST("/JsonAPI/webresources/CoreService/reset")
    Observable<ServerDataResponse<EmptyObject>> resetPassword(@Body PassResetRequest request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListOrderResponse>>> getListOrder(
            @Body DataRequest<GetListOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListChannelByOwnerTypeIdResponse>>>
    getListChannelByOwnerTypeId(
            @Body DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetAllInfoResponse>>> WS_GetAllInfo(
            @Body DataRequest<GetAllInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<TelecomServiceAndSaleProgramResponse>>>
    getTelecomserviceAndSaleProgram(
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
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransFromOrderResponse>>>
    createSaleTransFromOrder(
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
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransRetailResponse>>>
    createSaleTransRetail(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetDistributedChannelResponse>>>
    getDistributtedChannelInfo(
            @Body SearchBranchRequest request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateDistributedChannelResponse>>>
    createDistributtedChannel(
            @Body DataRequest<CreateDistributedChannelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransChannelResponse>>>
    createSaleTransChannel(
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
    Observable<ServerDataResponse<BaseResponse<CreateOrderResponse>>> createSaleOrders(
            @Body DataRequest<KPPOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<RegisterCustomerInfoResponse>>> registerCustomerInfo(
            @Body DataRequest<RegisterCustomerInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetRegisterSubInfoResponse>>> getRegiterSubInfo(
            @Body DataRequest<GetRegisterSubInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListBusTypeIdRequireResponse>>>
    getListBusTypeIdRequire(
            @Body DataRequest<GetListBusTypeIdRequireRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetApDomainByTypeResponse>>> getApDomainByType(
            @Body DataRequest<GetApDomainByTypeRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetOTPResponse>>> getOTP(
            @Body DataRequest<GetOTPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CheckOTPResponse>>> checOTP(
            @Body DataRequest<ChecOTPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetAllSubInfoResponse>>> getAllSubInfo(
            @Body DataRequest<GetAllSubInfoRequest> request);

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
    Observable<ServerDataResponse<BaseResponse<KPPRespondFeedbackResponse>>> kppResponseFeedback(
            @Body DataRequest<KPPRespondFeedbackRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> uploadImage(
            @Body DataRequest<UploadImageRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UserInfo>>> getUserInfo(
            @Body DataRequest<GetUserInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DownloadImageResponse>>> downloadImage(
            @Body DataRequest<DownloadImageRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListIdImageResponse>>> getListIdImage(
            @Body DataRequest<GetListIdImageRequest> request);

    /*get list stock trans detail by tran id*/
    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<ListStockTransDetailsReponse>>>
    getListStockTransDetail(
            @Body DataRequest<GetListStockTransDetailRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createExpStock(
            @Body DataRequest<CreateExpStockRequest> requestDataRequest);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListProductResponse>>> getListProduct(
            @Body DataRequest<GetListProductRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> isKppManager(
            @Body DataRequest<IsKPPManagerRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetSurveyKPPResponse>>> getSurveyKPPList(
            @Body DataRequest<GetSurveyKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> sendSurvey(
            @Body DataRequest<SendSurveyKPPRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListTeamResponse>>> getListTeam(
            @Body DataRequest<GetListTeamRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListDsLamByTeamIdResponse>>> getListDsLamByTeamId(
            @Body DataRequest<GetListDsLamByTeamIdRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetReceiverChangeAddressResponse>>>
    receiverChangeAddress(
            @Body AddressRequest<GetReceiverChangeAddressRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListSearchTransResponse>>> getListSearchTrans(
            @Body DataRequest<GetListSearchTransRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetSaleTransDetailResponse>>> getSaleTransDetail(
            @Body DataRequest<GetSaleTransDetailRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createInvoiceBill(
            @Body DataRequest<GetCreateInvoiceBillRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetRegisterSubResponse>>> getRegiterSub(
            @Body DataRequest<GetRegisterSubRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<InputOrderResponse>>> getListInvoice(
            @Body DataRequest<InputOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<InputOrderResponse>>> importInvoiceList(
            @Body DataRequest<InputOrderRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createExpStockNotHaveCmd(
            @Body DataRequest<CreateExpStockNotHaveCmdRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListRegTypeResponse>>> getListRegType(
            @Body DataRequest<GetListRegTypeRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListSubTypeResponse>>> getListSubType(
            @Body DataRequest<GetListSubTypeRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<ConnectSubscriberResponse>>> connectSubscriber(
            @Body DataRequest<ConnectSubscriberRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<BaseCreateCmdNote>>> createImportStock(
            @Body DataRequest<CreateImportStockRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListExpCmdResponse>>> getListExpCmd(
            @Body DataRequest<GetListExpCmdRequest> requestDataRequest);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<ViewInforSerialResponse>>> viewInfoSerial(
            @Body DataRequest<ViewInforSerialRequest> requestDataRequest);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListOwneCodeReponse>>> getListOwnerCode(
            @Body DataRequest<GetListOwnerCodeRequest> requestDataRequest);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<BaseCreateCmdNote>>> createImportCmd(
            @Body DataRequest<CreateImportCmdRequest> requestDataRequest);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<BaseCreateCmdNote>>> createImportNote(
            @Body DataRequest<CreateImportNoteRequest> requestDataRequest);

      @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> destroyStockTrans(
            @Body DataRequest<DestroyStockTransRequest> requestDataRequest);

    Observable<ServerDataResponse<BaseResponse<GetListBusTypeResponse>>> getListBusType(
            @Body DataRequest<GetListBusTypeRequest> request);
}

