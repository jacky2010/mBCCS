package com.viettel.mbccs.screen.stockdeliver;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverContract {
    interface ViewModel extends BaseView<StockDeliverContract.Presenter> {
        void onBackPress();
    }

    interface Presenter extends BasePresenter {

        void onSearchData();
    }
}
