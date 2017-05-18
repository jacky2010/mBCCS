package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentContract {
    interface Presenter extends BasePresenter,  OrderDetailAdapter.OrderDetailAdapterCallback{
    }

    interface View extends BaseView<Presenter> {
        void setData(List<GoodItem> items);
    }
}
