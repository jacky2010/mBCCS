package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import java.util.List;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public interface ISellOrdersRemoteDataSource {
    Observable<List<SaleOrders>> searchSellOrders(BaseRequest<GetListOrderRequest> request);

    Observable<List<ChannelInfo>> getListChannelByOwnerTypeId(
            BaseRequest<GetListChannelByOwnerTypeIdRequest> request);
}
