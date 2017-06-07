package com.viettel.mbccs.screen.transferanypay;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class TransferAnyPayContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
        void changeToSearchTab();
    }

    interface Presenter extends BasePresenter {
    }
}
