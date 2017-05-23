package com.viettel.mbccs.screen.sellanypay.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransAnyPayContract {
    interface ViewModel extends BaseView<Presenter> {
        void onTransCreatedSuccessful(ChangeSimItem item);
        void onTransFailed();
        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void createTransaction();
    }
}
