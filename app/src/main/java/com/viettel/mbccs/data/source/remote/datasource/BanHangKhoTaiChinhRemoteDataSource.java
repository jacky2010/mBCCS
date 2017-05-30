package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
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
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
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
    public Observable<GetListOrderResponse> getListOrder(
            DataRequest<GetListOrderRequest> request) {

        return RequestHelper.getRequest()
                .getListOrder(request)
                .flatMap(SchedulerUtils.<GetListOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            DataRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return RequestHelper.getRequest()
                .getListChannelByOwnerTypeId(request)
                .flatMap(SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetOrderInfoResponse> getOrderInfo(DataRequest<GetOrderInfoRequest> request) {
        return RequestHelper.getRequest()
                .getOrderInfo(request)
                .flatMap(SchedulerUtils.<GetOrderInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetOrderInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetReasonResponse> getListReason(DataRequest<GetResonRequest> request) {
        return RequestHelper.getRequest()
                .getListReason(request)
                .flatMap(SchedulerUtils.<GetReasonResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetReasonResponse>applyAsyncSchedulers());

    }

    @Override
    public Observable<GetListStockModelResponse> getListStockModel(
            DataRequest<GetListStockModelRequest> request) {
        return RequestHelper.getRequest()
                .getListStockModel(request)
                .flatMap(SchedulerUtils.<GetListStockModelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListStockModelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ViewInfoSerialResponse> viewInfoSerial(
            DataRequest<ViewInfoSerialRequest> request) {
        return RequestHelper.getRequest()
                .viewInfoSerial(request)
                .flatMap(SchedulerUtils.<ViewInfoSerialResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ViewInfoSerialResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(
            DataRequest<GetListProvinceRequest> request) {
        return RequestHelper.getRequest()
                .getListProvince(request)
                .flatMap(SchedulerUtils.<GetListProvinceResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListProvinceResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListTTKDResponse> getListTTKD(DataRequest<GetListTTKDRequest> request) {
        return RequestHelper.getRequest()
                .getListTTKD(request)
                .flatMap(SchedulerUtils.<GetListTTKDResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListTTKDResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListShopResponse> getListShop(DataRequest<GetListShopRequest> request) {
        return RequestHelper.getRequest()
                .getListShop(request)
                .flatMap(SchedulerUtils.<GetListShopResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListShopResponse>applyAsyncSchedulers());
    }

    public Observable<BaseResponse> createSaleOrders(
            DataRequest<KPPOrderRequest> requestBaseRequest) {
        return RequestHelper.getRequest()
                .createSaleOrders(requestBaseRequest)
                .compose(SchedulerUtils.<BaseResponse>applyAsyncSchedulers());
    }
}
