package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Reason;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetResonRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInfoSerialRequest;
import com.viettel.mbccs.data.source.remote.response.OrderInfoResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import java.util.List;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BanHangKhoTaiChinhRemoteDataSource implements IBanHangKhoTaiChinhRemoteDataSource {

    private volatile static BanHangKhoTaiChinhRemoteDataSource instance;

    private BanHangKhoTaiChinhRemoteDataSource() {
    }

    public static BanHangKhoTaiChinhRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BanHangKhoTaiChinhRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<List<SaleOrders>> searchSellOrders(BaseRequest<GetListOrderRequest> request) {
        return RequestHelper.getRequest()
                .searchSellOrders(request)
                .flatMap(SchedulerUtils.<List<SaleOrders>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<SaleOrders>>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<ChannelInfo>> getListChannelByOwnerTypeId(
            BaseRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return RequestHelper.getRequest()
                .getListChannelByOwnerTypeId(request)
                .flatMap(SchedulerUtils.<List<ChannelInfo>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<ChannelInfo>>applyAsyncSchedulers());
    }

    @Override
    public Observable<OrderInfoResponse> getOrderInfo(BaseRequest<GetOrderInfoRequest> request) {
        return RequestHelper.getRequest()
                .getOrderInfo(request)
                .flatMap(SchedulerUtils.<OrderInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<OrderInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<Reason>> getListReason(BaseRequest<GetResonRequest> request) {
        return null;
    }

    @Override
    public Observable<List<StockTotal>> getListStockModel(
            BaseRequest<GetListStockModelRequest> request) {
        return RequestHelper.getRequest()
                .getListStockModel(request)
                .flatMap(SchedulerUtils.<List<StockTotal>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<StockTotal>>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<StockSerial>> viewInfoSerial(
            BaseRequest<ViewInfoSerialRequest> request) {
        return RequestHelper.getRequest()
                .viewInfoSerial(request)
                .flatMap(SchedulerUtils.<List<StockSerial>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<StockSerial>>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<Area>> getListProvince(BaseRequest<GetListProvinceRequest> request) {
        return RequestHelper.getRequest()
                .getListProvince(request)
                .flatMap(SchedulerUtils.<List<Area>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<Area>>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<Shop>> getListTTKD(BaseRequest<GetListTTKDRequest> request) {
        return RequestHelper.getRequest()
                .getListTTKD(request)
                .flatMap(SchedulerUtils.<List<Shop>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<Shop>>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<Shop>> getListShop(BaseRequest<GetListShopRequest> request) {
        return RequestHelper.getRequest()
                .getListShop(request)
                .flatMap(SchedulerUtils.<List<Shop>>convertDataFlatMap())
                .compose(SchedulerUtils.<List<Shop>>applyAsyncSchedulers());
    }
}
