package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.ISellOrdersRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import java.util.List;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class SellOrdersRepository implements ISellOrdersRemoteDataSource {
    private volatile static SellOrdersRepository instance;

    public static SellOrdersRepository getInstance() {
        if (instance == null) {
            instance = new SellOrdersRepository();
        }
        return instance;
    }

    private SellOrdersRepository() {
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
}
