package com.viettel.mbccs.screen.kpp.order.ordersuccess;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

public interface OrderSuccessContract {

    interface Presenter extends BasePresenter {

    }

    interface ViewModel extends BaseView<Presenter> {

        void closeClick();
    }
}
