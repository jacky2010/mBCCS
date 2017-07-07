package com.viettel.mbccs.base.createorder;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTrans;

public interface BaseCreateOrderSuccessContract {

    interface ViewModel<T extends Presenter> extends BaseView<T> {

        void onBackPressed();


    }

    interface Presenter extends BasePresenter {

    }
}