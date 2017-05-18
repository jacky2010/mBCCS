package com.viettel.mbccs.screen.sell.orders;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SaleOrders;
import java.util.List;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersContract {
    interface Presenter extends BasePresenter, AdapterView.OnItemSelectedListener {
        void setSpinnerAdapterUnit(ArrayAdapter adapterUnit);
    }

    interface View extends BaseView<Presenter> {

        void setDataResult(List<SaleOrders> orders);

        void getDataError();
    }
}
