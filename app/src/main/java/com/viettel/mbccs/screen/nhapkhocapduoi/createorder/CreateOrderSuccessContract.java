package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessContract;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;

public interface CreateOrderSuccessContract {

    interface ViewModel extends BaseCreateOrderSuccessContract.ViewModel {

        void showSerialViewer(StockTransDetail item);
    }

    interface Presenter extends BaseCreateOrderSuccessContract.Presenter {

    }
}