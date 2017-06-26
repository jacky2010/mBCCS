package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessContract;
import com.viettel.mbccs.data.model.StockTotal;

public interface CreateOrderSuccessContract {

    interface ViewModel extends BaseCreateOrderSuccessContract.ViewModel {

        void showSerialViewer(StockTotal item);
    }

    interface Presenter extends BaseCreateOrderSuccessContract.Presenter {

    }
}