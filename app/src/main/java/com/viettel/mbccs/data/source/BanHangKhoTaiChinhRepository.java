package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.local.IBanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.BanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotHaveCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
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
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.InputOrderRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.InputOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BanHangKhoTaiChinhRepository
        implements IBanHangKhoTaiChinhLocalDataSource, IBanHangKhoTaiChinhRemoteDataSource {
    private volatile static BanHangKhoTaiChinhRepository instance;
    private IBanHangKhoTaiChinhLocalDataSource banHangKhoTaiChinhLocalDataSource;
    private IBanHangKhoTaiChinhRemoteDataSource banHangKhoTaiChinhRemoteDataSource;

    private BanHangKhoTaiChinhRepository(
            BanHangKhoTaiChinhRemoteDataSource banHangKhoTaiChinhRemoteDataSource,
            BanHangKhoTaiChinhLocalDataSource banHangKhoTaiChinhLocalDataSource) {
        this.banHangKhoTaiChinhRemoteDataSource = banHangKhoTaiChinhRemoteDataSource;
        this.banHangKhoTaiChinhLocalDataSource = banHangKhoTaiChinhLocalDataSource;
    }

    public static BanHangKhoTaiChinhRepository getInstance() {
        if (instance == null) {
            instance = new BanHangKhoTaiChinhRepository(
                    BanHangKhoTaiChinhRemoteDataSource.getInstance(),
                    BanHangKhoTaiChinhLocalDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetListOrderResponse> getListOrder(DataRequest<GetListOrderRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListOrder(request);
    }

    @Override
    public Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            DataRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListChannelByOwnerTypeId(request);
    }

    @Override
    public Observable<GetOrderInfoResponse> getOrderInfo(DataRequest<GetOrderInfoRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getOrderInfo(request);
    }

    @Override
    public Observable<GetReasonResponse> getReason(DataRequest<GetReasonRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getReason(request);
    }

    @Override
    public Observable<UpdateSaleOrderResponse> updateSaleOrder(
            DataRequest<UpdateSaleOrderRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.updateSaleOrder(request);
    }

    @Override
    public Observable<CreateSaleTransFromOrderResponse> createSaleTransFromOrder(
            DataRequest<CreateSaleTransFromOrderRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleTransFromOrder(request);
    }

    @Override
    public Observable<GetListStockModelResponse> getListStockModel(
            DataRequest<GetListStockModelRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListStockModel(request);
    }

    @Override
    public Observable<GetListSerialResponse> getListSerial(
            DataRequest<GetListSerialRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListSerial(request);
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(
            DataRequest<GetListProvinceRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListProvince(request);
    }

    @Override
    public Observable<GetListTTKDResponse> getListTTKD(DataRequest<GetListTTKDRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListTTKD(request);
    }

    @Override
    public Observable<GetListShopResponse> getListShop(DataRequest<GetListShopRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListShop(request);
    }

    public Observable<EmptyObject> createSaleOrders(
            DataRequest<KPPOrderRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleOrders(requestDataRequest);
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getTelecomserviceAndSaleProgram(request);
    }

    @Override
    public Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getSerial(request);
    }

    @Override
    public Observable<GetTotalStockResponse> getModelSales(
            DataRequest<GetTotalStockRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getModelSales(request);
    }

    @Override
    public Observable<GetInfoSaleTranResponse> getSaleTransInfo(
            DataRequest<GetInfoSaleTranRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getSaleTransInfo(request);
    }

    @Override
    public Observable<CreateSaleTransRetailResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleTransRetail(request);
    }

    @Override
    public Observable<CreateSaleTransChannelResponse> createSaleTransChannel(
            DataRequest<CreateSaleTransChannelRequest> requestBaseRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleTransChannel(requestBaseRequest);
    }

    @Override
    public Observable<ListStockTransDetailsReponse> getListStockTransDetail(
            DataRequest<GetListStockTransDetailRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListStockTransDetail(request);
    }

    @Override
    public Observable<EmptyObject> createExpStock(
            DataRequest<CreateExpStockRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createExpStock(requestDataRequest);
    }

    @Override
    public Observable<InputOrderResponse> getListInvoice(
            DataRequest<InputOrderRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.getListInvoice(requestDataRequest);
    }

    @Override
    public Observable<InputOrderResponse> importInvoiceList(
            DataRequest<InputOrderRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.importInvoiceList(requestDataRequest);
    }

    public Observable<EmptyObject> createExpStockNotHaveCmd(
            DataRequest<CreateExpStockNotHaveCmdRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createExpStockNotHaveCmd(requestDataRequest);
    }
}
