package com.viettel.mbccs.screen.inputorder;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

public interface InputOrderContract {

    interface ViewModel extends BaseView<Presenter> {
        void onClickBack();
    }

    interface Presenter extends BasePresenter {
        void onBackClick();
    }
}
