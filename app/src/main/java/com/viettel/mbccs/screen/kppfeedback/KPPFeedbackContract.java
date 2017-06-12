package com.viettel.mbccs.screen.kppfeedback;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class KPPFeedbackContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
        void changeToSearchTab();
        void changeToSendTab();
    }

    interface Presenter extends BasePresenter {
    }
}
