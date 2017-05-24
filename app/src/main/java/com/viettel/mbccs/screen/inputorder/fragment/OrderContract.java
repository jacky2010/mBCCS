package com.viettel.mbccs.screen.inputorder.fragment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.screen.inputorder.InputOrderContract;

public interface OrderContract {
    interface ViewModel extends BaseView<InputOrderContract.Presenter> {
        void inputOrderSuccess();
    }

    interface Presenter extends BasePresenter {
        void onInputOrderClick();
    }
}
