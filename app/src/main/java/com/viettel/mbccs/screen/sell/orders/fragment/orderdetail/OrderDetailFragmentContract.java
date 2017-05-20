package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentContract {
    interface Presenter extends BasePresenter,  OrderDetailAdapter.OrderDetailAdapterCallback{
        void onSerialPickerSuccess(List<SerialBO> serialBlockBySerials);
    }

    interface View extends BaseView<Presenter> {
//        void setData(List<ModelSale> items);
        void setData(List<SaleOrdersDetail> items);

        void getOrderInfoError(BaseException error);

        void pickSerial(ModelSale modelSale);
    }
}
