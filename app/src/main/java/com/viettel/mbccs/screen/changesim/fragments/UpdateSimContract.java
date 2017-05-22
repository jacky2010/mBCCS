package com.viettel.mbccs.screen.changesim.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class UpdateSimContract {
    interface ViewModel extends BaseView<Presenter> {
        void onChangeSimSuccessful(ChangeSimItem item);
        void onChangeSimFailed();
        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void changeSim();
    }
}
