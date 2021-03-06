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
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetHotNewsInfoCSKPPRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetKPPFeedbackRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListIdImageRequest;
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
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetHotNewsInfoCSKPPResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetKPPFeedbackResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
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

    // TODO: 12/06/2017 Fake request
    @POST("/JsonAPI/webresources/CoreService/login")
    Observable<LoginInfo> login(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("/send_code_password")
    Observable<ServerDataResponse<BaseResponse<SendCodeChangePassResponse>>> sendCodeChangePass(
            @Field("phone") String phone);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetAllInfo")
    Observable<ServerDataResponse<EmptyObject>> resetPassword(@Body PassResetRequest request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetListOrder")
    Observable<ServerDataResponse<BaseResponse<GetListOrderResponse>>> getListOrder(
            @Body DataRequest<GetListOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_getListChannelByOwnerTypeId")
    Observable<ServerDataResponse<BaseResponse<GetListChannelByOwnerTypeIdResponse>>>
    getListChannelByOwnerTypeId(
            @Body DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetAllInfo")
    Observable<ServerDataResponse<BaseResponse<GetAllInfoResponse>>> WS_GetAllInfo(
            @Body DataRequest<GetAllInfoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetTelecomServiceAndSaleProgram")
    Observable<ServerDataResponse<BaseResponse<TelecomServiceAndSaleProgramResponse>>>
    getTelecomserviceAndSaleProgram(
            @Body DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetListSerial")
    Observable<ServerDataResponse<BaseResponse<GetSerialsResponse>>> getSerials(
            @Body DataRequest<GetSerialRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetOrderInfo")
    Observable<ServerDataResponse<BaseResponse<GetOrderInfoResponse>>> getOrderInfo(
            @Body DataRequest<GetOrderInfoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetReason")
    Observable<ServerDataResponse<BaseResponse<GetReasonResponse>>> getReason(
            @Body DataRequest<GetReasonRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransFromOrder")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransFromOrderResponse>>>
    createSaleTransFromOrder(
            @Body DataRequest<CreateSaleTransFromOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_UpdateSaleOrder")
    Observable<ServerDataResponse<BaseResponse<UpdateSaleOrderResponse>>> updateSaleOrder(
            @Body DataRequest<UpdateSaleOrderRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetStockTotal")
    Observable<ServerDataResponse<BaseResponse<GetTotalStockResponse>>> getModelSales(
            @Body DataRequest<GetTotalStockRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_getSaleTransInfo")
    Observable<ServerDataResponse<BaseResponse<GetInfoSaleTranResponse>>> getSaleTransInfo(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransRetail")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransRetailResponse>>>
    createSaleTransRetail(
            @Body DataRequest<GetInfoSaleTranRequest> request);

    @POST("/getDistributtedChannelInfo")
    Observable<ServerDataResponse<BaseResponse<GetDistributedChannelResponse>>>
    getDistributtedChannelInfo(
            @Body SearchBranchRequest request);

    @POST("/createDistributtedChannel")
    Observable<ServerDataResponse<BaseResponse<CreateDistributedChannelResponse>>>
    createDistributtedChannel(
            @Body DataRequest<CreateDistributedChannelRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CreateSaleTransChannel")
    Observable<ServerDataResponse<BaseResponse<CreateSaleTransChannelResponse>>>
    createSaleTransChannel(
            @Body DataRequest<CreateSaleTransChannelRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_getListStockModel")
    Observable<ServerDataResponse<BaseResponse<GetListStockModelResponse>>> getListStockModel(
            @Body DataRequest<GetListStockModelRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_GetListSerial")
    Observable<ServerDataResponse<BaseResponse<GetListSerialResponse>>> getListSerial(
            @Body DataRequest<GetListSerialRequest> request);

    @POST("/getListProvince")
    Observable<ServerDataResponse<BaseResponse<GetListProvinceResponse>>> getListProvince(
            @Body DataRequest<GetListProvinceRequest> request);

    @POST("/getListTTKD")
    Observable<ServerDataResponse<BaseResponse<GetListTTKDResponse>>> getListTTKD(
            @Body DataRequest<GetListTTKDRequest> request);

    @POST("/getListTTKD")
    Observable<ServerDataResponse<BaseResponse<GetListShopResponse>>> getListShop(
            @Body DataRequest<GetListShopRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_createSaleOrders")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createSaleOrders(
            @Body DataRequest<KPPOrderRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_registerCustomerInfo")
    Observable<ServerDataResponse<BaseResponse<RegisterCustomerInfoResponse>>> registerCustomerInfo(
            @Body DataRequest<RegisterCustomerInfoRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getRegiterSubInfo")
    Observable<ServerDataResponse<BaseResponse<GetRegiterSubInfoResponse>>> getRegiterSubInfo(
            @Body DataRequest<GetRegiterSubInfoRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getListBusTypeIdRequire")
    Observable<ServerDataResponse<BaseResponse<GetListBusTypeIdRequireResponse>>>
    getListBusTypeIdRequire(
            @Body DataRequest<GetListBusTypeIdRequireRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getApDomain")
    Observable<ServerDataResponse<BaseResponse<GetApDomainByTypeResponse>>> getApDomainByType(
            @Body DataRequest<GetApDomainByTypeRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getOTP")
    Observable<ServerDataResponse<BaseResponse<GetOTPResponse>>> getOTP(
            @Body DataRequest<GetOTPRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_checOTP")
    Observable<ServerDataResponse<BaseResponse<CheckOTPResponse>>> checOTP(
            @Body DataRequest<ChecOTPRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_updateAllSubInfo")
    Observable<ServerDataResponse<BaseResponse<UpdateAllSubInfoResponse>>> updateAllSubInfo(
            @Body DataRequest<UpdateAllSubInfoRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getProvince")
    Observable<ServerDataResponse<BaseResponse<GetProvinceResponse>>> getProvince(
            @Body DataRequest<GetProvinceRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getDistrict")
    Observable<ServerDataResponse<BaseResponse<GetDistrictResponse>>> getDistrict(
            @Body DataRequest<GetDistrictRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_getPrecinct")
    Observable<ServerDataResponse<BaseResponse<GetPrecinctResponse>>> getPrecinct(
            @Body DataRequest<GetPrecinctRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_checkIdNo")
    Observable<ServerDataResponse<BaseResponse<CheckIdNoResponse>>> checkIdNo(
            @Body DataRequest<CheckIdNoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_CheckAuthenticate")
    Observable<ServerDataResponse<BaseResponse<GetAnypayAuthorizeResponse>>> getAnypayAuthorize(
            @Body DataRequest<GetAnypayAuthorizeRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_SaleAnyPayToCustomer")
    Observable<ServerDataResponse<BaseResponse<SellAnypayToCustomerResponse>>> saleAnypayToCustomer(
            @Body DataRequest<SellAnypayToCustomerRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_SaleAnyPayToChannel")
    Observable<ServerDataResponse<BaseResponse<SellAnypayToChannelResponse>>> saleAnypayToChannel(
            @Body DataRequest<SellAnypayToChannelRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransferAnypay")
    Observable<ServerDataResponse<BaseResponse<TransferAnyPayResponse>>> transferAnyPay(
            @Body DataRequest<TransferAnyPayRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<RefillAnyPayResponse>>> refillAnyPay(
            @Body DataRequest<RefillAnyPayRequest> request);

    //    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    //    Observable<ServerDataResponse<BaseResponse<GetRegisterSubResponse>>> getRegisterSub(
    //            @Body DataRequest<GetRegisterSubRequest> request);
    //
    //    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    //    Observable<ServerDataResponse<BaseResponse<CheckCalledIsdnResponse>>> checkCalledIsdn(
    //            @Body DataRequest<CheckCalledIsdnRequest> request);
    //
    //    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    //    Observable<ServerDataResponse<BaseResponse<UpdateRegisterSubResponse>>> updateRegisterSub(
    //            @Body DataRequest<UpdateRegisterSubRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> checkChangeSimDebit(
            @Body DataRequest<CheckDebitChangeSimRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> checkCalledIsdnChangeSim(
            @Body DataRequest<CheckCalledIsdnsRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DataResponse>>> changeSim(
            @Body DataRequest<ChangeSimRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<GetSurveyKPPResponse>>> getSurveyKPP(
            @Body DataRequest<GetSurveyKPPRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<SendSurveyKPPResponse>>> sendSurveyKPP(
            @Body DataRequest<SendSurveyKPPRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<GetHotNewsCSKPPResponse>>> getHotNews(
            @Body DataRequest<GetHotNewsCSKPPRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<GetHotNewsInfoCSKPPResponse>>> getHotNewsInfo(
            @Body DataRequest<GetHotNewsInfoCSKPPRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<GetKPPFeedbackResponse>>> getKPPFeedback(
            @Body DataRequest<GetKPPFeedbackRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<GetKPPFeedbackInfoResponse>>> getKPPFeedbackInfo(
            @Body DataRequest<GetKPPFeedbackInfoRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<KPPFeedbackResponse>>> kppFeedback(
            @Body DataRequest<KPPFeedbackRequest> request);

    @POST("thonguyen/Sale_mBCCS/1.0.0/WS_TransReloadAnypay")
    Observable<ServerDataResponse<BaseResponse<KPPResponseFeedbackResponse>>> kppResponseFeedback(
            @Body DataRequest<KPPResponseFeedbackRequest> request);

    @POST("vietdt/CM_mBCCS/1.0.0/WS_upLoad")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> uploadImage(
            @Body DataRequest<UploadImageRequest> request);

    //Nhap hoa don API
    @GET("vietdt/CM_mBCCS/1.0.0/Ws_importInvoiceList")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> getImportInvoiceList(
            @Body DataRequest<UploadImageRequest> request);

    @GET("vietdt/CM_mBCCS/1.0.0/WS_getListInvoiceImport")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> getListInvoiceImport(
            @Body DataRequest<UploadImageRequest> request);

    @GET("vietdt/CM_mBCCS/1.0.0/Ws_getListInvoiceExport")
    Observable<ServerDataResponse<BaseResponse<UploadImageResponse>>> getListInvoiceExport(
            @Body DataRequest<UploadImageRequest> request);
    //End API Nhap hoa don

    // TODO: 12/06/2017 Fake request
    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UserInfo>>> getUserInfo(
            @Body DataRequest<GetUserInfoRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListIdImageResponse>>> getListIdImage(
            @Body DataRequest<GetListIdImageRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<DownloadImageResponse>>> downloadImage(
            @Body DataRequest<DownloadImageRequest> request);

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
    Observable<ServerDataResponse<BaseResponse<GetRegisterSubResponse>>> getRegiterSub(
            @Body DataRequest<GetRegisterSubRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<EmptyObject>>> createExpStockNotHaveCmd(
            @Body DataRequest<CreateExpStockNotHaveCmdRequest> request);

    // Assign Task
    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetTaskPrepareAssignStaffResponse>>> getTaskPrepareAssignStaff(
            @Body DataRequest<GetTaskPrepareAssignStaffRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<AssignTaskForStaffResponse>>> assignTaskForStaff(
            @Body DataRequest<AssignTaskForStaffRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetInfoTaskForUpdateResponse>>> getInfoTaskForUpdate(
            @Body DataRequest<GetInfoTaskForUpdateRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetAccessoryForUpdateTaskResponse>>> getAccessoryForUpdateTask(
            @Body DataRequest<GetAccessoryForUpdateTaskRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetStockModelForUpdateTaskResponse>>> getStockModelForUpdateTask(
            @Body DataRequest<GetStockModelForUpdateTaskRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<UpdateTaskResponse>>> updateTask(
            @Body DataRequest<UpdateTaskRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CloseTaskResponse>>> closeTask(
            @Body DataRequest<CloseTaskRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetStaffToAssignTaskResponse>>> getStaffToAssignTask(
            @Body DataRequest<GetStaffToAssignTaskRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetChannelRouterResponse>>> getChannelRouter(
            @Body DataRequest<GetChannelRouterRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetChannelWorkTypeResponse>>> getChannelWorkType(
            @Body DataRequest<GetChannelWorkTypeRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetListChannelResponse>>> getListChannel(
            @Body DataRequest<GetListChannelRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<CreateTaskExtendResponse>>> createTaskExtend(
            @Body DataRequest<CreateTaskExtendRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SearchProductResponse>>> searchProduct(
            @Body DataRequest<SearchProductRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<SearchAdvancedProductResponse>>> searchAdvancedProduct(
            @Body DataRequest<SearchAdvancedProductRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetProductSearchResponse>>> getProductSearch(
            @Body DataRequest<GetProductSearchRequest> request);

    @POST("/JsonAPI/webresources/CoreService/UserRouting")
    Observable<ServerDataResponse<BaseResponse<GetProductInfoResponse>>> getProductInfo(
            @Body DataRequest<GetProductInfoRequest> request);
}
