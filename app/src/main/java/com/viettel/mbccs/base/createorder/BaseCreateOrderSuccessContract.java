package com.viettel.mbccs.base.createorder;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

public interface BaseCreateOrderSuccessContract {

    interface ViewModel<T extends Presenter> extends BaseView<T> {

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

    }
}