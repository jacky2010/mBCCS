package com.viettel.mbccs.screen.trahangcaptren.create;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.List;

/**
 * Created by FRAMGIA\vu.viet.anh on 07/06/2017.
 */

public interface CreateTicketContract {

    interface ViewModel
            extends BaseSearchListViewContract.ViewModel<Presenter> {

        void onCreateTicket();

        void onAddGoods();
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {

        void pickStockTotalListSuccess(List<StockTotal> stockTotals);
    }
}
