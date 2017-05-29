package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.Reason;
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
import com.viettel.mbccs.data.source.remote.response.ViewInfoSerialResponse;
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
    public Observable<GetListOrderResponse> searchSellOrders(
            BaseRequest<GetListOrderRequest> request) {
        return RequestHelper.getRequest()
                .getListOrder(request)
                .flatMap(SchedulerUtils.<GetListOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            BaseRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return RequestHelper.getRequest()
                .getListChannelByOwnerTypeId(request)
                .flatMap(SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetOrderInfoResponse> getOrderInfo(BaseRequest<GetOrderInfoRequest> request) {
        return RequestHelper.getRequest()
                .getOrderInfo(request)
                .flatMap(SchedulerUtils.<GetOrderInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetOrderInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<List<Reason>> getListReason(BaseRequest<GetResonRequest> request) {
        return null;
    }

    @Override
    public Observable<GetListStockModelResponse> getListStockModel(
            BaseRequest<GetListStockModelRequest> request) {
        return RequestHelper.getRequest()
                .getListStockModel(request)
                .flatMap(SchedulerUtils.<GetListStockModelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListStockModelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ViewInfoSerialResponse> viewInfoSerial(
            BaseRequest<ViewInfoSerialRequest> request) {
        return RequestHelper.getRequest()
                .viewInfoSerial(request)
                .flatMap(SchedulerUtils.<ViewInfoSerialResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ViewInfoSerialResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(
            BaseRequest<GetListProvinceRequest> request) {
        return RequestHelper.getRequest()
                .getListProvince(request)
                .flatMap(SchedulerUtils.<GetListProvinceResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListProvinceResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListTTKDResponse> getListTTKD(BaseRequest<GetListTTKDRequest> request) {
        return RequestHelper.getRequest()
                .getListTTKD(request)
                .flatMap(SchedulerUtils.<GetListTTKDResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListTTKDResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListShopResponse> getListShop(BaseRequest<GetListShopRequest> request) {
        return RequestHelper.getRequest()
                .getListShop(request)
                .flatMap(SchedulerUtils.<GetListShopResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListShopResponse>applyAsyncSchedulers());
    }

    public Observable<BaseResponse> createSaleOrders(
            BaseRequest<KPPOrderRequest> requestBaseRequest) {
        return RequestHelper.getRequest()
                .createSaleOrders(requestBaseRequest)
                .compose(SchedulerUtils.<BaseResponse>applyAsyncSchedulers());
    }
}
