package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.remote.request.CreateExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteNoCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetStockTransSerialDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.InputOrderRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInforSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetStockTransSerialDetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.InputOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ViewInforSerialResponse;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public interface IBanHangKhoTaiChinhRemoteDataSource {
    Observable<GetListOrderResponse> getListOrder(DataRequest<GetListOrderRequest> request);

    Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    Observable<GetOrderInfoResponse> getOrderInfo(DataRequest<GetOrderInfoRequest> request);

    Observable<GetReasonResponse> getReason(DataRequest<GetReasonRequest> request);

    Observable<UpdateSaleOrderResponse> updateSaleOrder(
            DataRequest<UpdateSaleOrderRequest> request);

    Observable<CreateSaleTransFromOrderResponse> createSaleTransFromOrder(
            DataRequest<CreateSaleTransFromOrderRequest> request);

    Observable<GetListStockModelResponse> getListStockModel(
            DataRequest<GetListStockModelRequest> request);

    Observable<GetListSerialResponse> getListSerial(DataRequest<GetListSerialRequest> request);

    Observable<GetListProvinceResponse> getListProvince(
            DataRequest<GetListProvinceRequest> request);

    Observable<GetListTTKDResponse> getListTTKD(DataRequest<GetListTTKDRequest> request);

    Observable<GetListShopResponse> getListShop(DataRequest<GetListShopRequest> request);

    Observable<CreateOrderResponse> createSaleOrders(
            DataRequest<KPPOrderRequest> requestDataRequest);

    Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request);

    Observable<GetTotalStockResponse> getModelSales(DataRequest<GetTotalStockRequest> request);

    Observable<GetInfoSaleTranResponse> getSaleTransInfo(
            DataRequest<GetInfoSaleTranRequest> request);

    Observable<CreateSaleTransRetailResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request);

    Observable<CreateSaleTransChannelResponse> createSaleTransChannel(
            DataRequest<CreateSaleTransChannelRequest> requestDataRequest);

    Observable<ListStockTransDetailsReponse> getListStockTransDetail(
            DataRequest<GetListStockTransDetailRequest> request);

    Observable<BaseCreateCmdNoteResponse> createExpStock(DataRequest<CreateExpStockRequest> requestDataRequest);

    Observable<InputOrderResponse> getListInvoice(
            DataRequest<InputOrderRequest> requestDataRequest);

    Observable<EmptyObject> importInvoiceList(
            DataRequest<InputOrderRequest> requestDataRequest);

    Observable<BaseCreateCmdNoteResponse> createExpStockNotNote(
            DataRequest<CreateExpStockNotNoteRequest> requestDataRequest);

    Observable<BaseCreateCmdNoteResponse> createImportStock(
            DataRequest<CreateImportStockRequest> requestDataRequest);

    Observable<GetListExpCmdResponse> getListExpCmd(DataRequest<GetListExpCmdRequest> dataRequest);

    Observable<ViewInforSerialResponse> viewInforSerial(
            DataRequest<ViewInforSerialRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createImportCmd(
            DataRequest<CreateImportCmdRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createImportNote(
            DataRequest<CreateImportNoteRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createImportNoteNoCMD(
            DataRequest<CreateImportNoteRequest> dataRequest);

    Observable<EmptyObject> destroyStockTrans(DataRequest<DestroyStockTransRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createExpCmd(
            DataRequest<CreateExpCmdRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createExpNote(
            DataRequest<CreateExpNoteRequest> dataRequest);

    Observable<BaseCreateCmdNoteResponse> createExpNoteNoCmd(
            DataRequest<CreateExpNoteNoCmdRequest> dataRequest);

    Observable<GetStockTransSerialDetailResponse> getStockTransDetailSerial(
            DataRequest<GetStockTransSerialDetailRequest> dataRequest);
}
