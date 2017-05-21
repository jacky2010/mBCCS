package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Reason;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.local.IBanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BanHangKhoTaiChinhLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.BanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
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
import com.viettel.mbccs.data.source.remote.response.OrderInfoResponse;
import java.util.List;
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
    public Observable<List<SaleOrders>> searchSellOrders(BaseRequest<GetListOrderRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.searchSellOrders(request);
    }

    @Override
    public Observable<List<ChannelInfo>> getListChannelByOwnerTypeId(
            BaseRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListChannelByOwnerTypeId(request);
    }

    @Override
    public Observable<OrderInfoResponse> getOrderInfo(BaseRequest<GetOrderInfoRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getOrderInfo(request);
    }

    @Override
    public Observable<List<Reason>> getListReason(BaseRequest<GetResonRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListReason(request);
    }

    @Override
    public Observable<List<StockTotal>> getListStockModel(
            BaseRequest<GetListStockModelRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListStockModel(request);
    }

    @Override
    public Observable<List<StockSerial>> viewInfoSerial(
            BaseRequest<ViewInfoSerialRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.viewInfoSerial(request);
    }

    @Override
    public Observable<List<Area>> getListProvince(BaseRequest<GetListProvinceRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListProvince(request);
    }

    @Override
    public Observable<List<Shop>> getListTTKD(BaseRequest<GetListTTKDRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListTTKD(request);
    }

    @Override
    public Observable<List<Shop>> getListShop(BaseRequest<GetListShopRequest> request) {
        return banHangKhoTaiChinhRemoteDataSource.getListShop(request);
    }

    public Observable<BaseResponse> createSaleOrders(
            BaseRequest<KPPOrderRequest> requestBaseRequest) {
        return banHangKhoTaiChinhRemoteDataSource.createSaleOrders(requestBaseRequest);
    }
}
