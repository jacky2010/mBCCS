package com.viettel.mbccs.screen.kpp.order.addnew;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class AddNewOrderContract {

    interface Presenter extends BasePresenter {

        void pickStockTotalListSuccess(List<StockTotal> stockTotals);
    }

    interface ViewModel extends BaseView<Presenter> {

        void goGoStockPicker();

        void gotoSuccessScreen(ArrayList<StockTotal> stockTotals, String saleOrderId,
                String channelName);
    }
}
