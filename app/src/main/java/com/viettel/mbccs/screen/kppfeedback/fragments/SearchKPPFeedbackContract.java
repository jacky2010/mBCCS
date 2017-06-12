package com.viettel.mbccs.screen.kppfeedback.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KPPFeedback;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchKPPFeedbackContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onSearchCompleted();
        void openFeedbackDetail(KPPFeedback item);
    }

    interface Presenter extends BasePresenter {
        void searchFeedbacks();
    }
}
