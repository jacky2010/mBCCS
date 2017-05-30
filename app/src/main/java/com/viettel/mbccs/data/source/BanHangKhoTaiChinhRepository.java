package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.IBanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.BanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetResonRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.ViewInfoSerialResponse;
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
    public Observable<GetReasonResponse> getListReason(DataRequest<GetResonRequest> request) {

        return banHangKhoTaiChinhRemoteDataSource.getListReason(request);
    }

    @Override
    public Observable<GetListStockModelResponse> getListStockModel(
            DataRequest<GetListStockModelRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListStockModel(request);
    }

    @Override
    public Observable<ViewInfoSerialResponse> viewInfoSerial(
            DataRequest<ViewInfoSerialRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.viewInfoSerial(request);
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(DataRequest<GetListProvinceRequest> request) {
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

    public Observable<BaseResponse> createSaleOrders(
            DataRequest<KPPOrderRequest> requestDataRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleOrders(requestDataRequest);
    }
}
