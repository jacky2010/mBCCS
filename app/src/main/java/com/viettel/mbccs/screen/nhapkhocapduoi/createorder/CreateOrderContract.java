package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import com.viettel.mbccs.base.createorder.BaseCreateOrderContract;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;

/**
 * Created by Anh Vu Viet on 6/26/2017.
 */

public interface CreateOrderContract {

    interface ViewModel extends BaseCreateOrderContract.ViewModel {

        void onViewSerialClickListener(StockTransDetail item);
    }

    interface Presenter extends BaseCreateOrderContract.Presenter {

    }
}
