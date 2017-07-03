package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentContract {
    interface Presenter extends BasePresenter,  OrderDetailAdapter.OrderDetailAdapterCallback{
    }

    interface View extends BaseView<Presenter> {
        void setData(GetOrderInfoResponse data);

        void getOrderInfoError(BaseException error);

        void pickSerial(SaleOrdersDetail saleOrdersDetail);

        void clickCancelSell(SaleTrans saleTrans);

        void onClickSell(SaleTrans saleTrans);

        void checkCountSerialError();

        void getTranInfoError(BaseException error);
    }
}
