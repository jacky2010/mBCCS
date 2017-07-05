package com.viettel.mbccs.screen.stockdeliver.createcommand;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.ArrayList;
import java.util.List;

public class CreateCommandContract {

    interface Presenter extends BasePresenter {

        void pickStockTotalListSuccess(List<StockTotal> stockTotals);
        String getActivityTitle();
    }

    interface ViewModel extends BaseView<Presenter> {

        String getScreenTitle();

        void goGoStockPicker(ArrayList<StockTotal> stockTotals);

        void gotoSuccessScreen(ArrayList<StockTotal> stockTotals, String saleOrderId,
                String channelName);

        void finishScreen();
    }
}
