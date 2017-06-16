package com.viettel.mbccs.screen.kpp.order.findstock;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.ArrayList;
import java.util.List;

public interface FindStockContract {

    interface Presenter extends BasePresenter {

        void stockTypeSelected(int position);
    }

    interface ViewModel extends BaseView<Presenter> {

        void returnListStockTotal(ArrayList<StockTotal> stockTotalsSaved);

        void closeForm();
    }

}
