package com.viettel.mbccs.data.source.remote;


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

public interface IBanHangKhoTaiChinhRemoteDataSource {
    Observable<GetListOrderResponse> getListOrder(DataRequest<GetListOrderRequest> request);


    Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            DataRequest<GetListChannelByOwnerTypeIdRequest> request);

    Observable<GetOrderInfoResponse> getOrderInfo(DataRequest<GetOrderInfoRequest> request);

    Observable<GetReasonResponse> getListReason(DataRequest<GetResonRequest> request);


    Observable<GetListStockModelResponse> getListStockModel(DataRequest<GetListStockModelRequest> request);

    Observable<ViewInfoSerialResponse> viewInfoSerial(DataRequest<ViewInfoSerialRequest> request);

    Observable<GetListProvinceResponse> getListProvince(DataRequest<GetListProvinceRequest> request);

    Observable<GetListTTKDResponse> getListTTKD(DataRequest<GetListTTKDRequest> request);

    Observable<GetListShopResponse> getListShop(DataRequest<GetListShopRequest> request);

    Observable<BaseResponse> createSaleOrders(DataRequest<KPPOrderRequest> requestDataRequest);
}
